package org.mskcc.igo.pi.external.jpa.gateway;

import org.mskcc.domain.external.ExternalRun;
import org.mskcc.igo.pi.external.jpa.converter.ExternalRunConverter;
import org.mskcc.igo.pi.external.jpa.entity.ExternalRunEntity;
import org.mskcc.igo.pi.external.rest.run.ExternalRunGateway;
import org.mskcc.igo.pi.external.rest.run.ExternalRunRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JPAExternalRunGateway implements ExternalRunGateway {
    private ExternalRunRepository externalRunRepository;

    public JPAExternalRunGateway(ExternalRunRepository externalRunRepository) {
        this.externalRunRepository = externalRunRepository;
    }

    @Override
    public Optional<ExternalRun> getRunByAnonymizedId(String anonymizedId) {
        Optional<ExternalRunEntity> externalRunEntity = externalRunRepository.findByAnonymizedRunId(anonymizedId);

        if(externalRunEntity.isPresent())
            return Optional.of(ExternalRunConverter.convert(externalRunEntity.get()));
        return Optional.empty();
    }

    @Override
    public void addRun(ExternalRun externalRun) {
        validateRunIdMapping(externalRun);
        externalRunRepository.save(ExternalRunConverter.convert(externalRun));
    }

    private void validateRunIdMapping(ExternalRun externalRun) {
        Optional<ExternalRun> existingExternalRun = getRunByAnonymizedId(externalRun.getAnonymizedId());

        if (existingExternalRun.isPresent() && !isMappingTheSame(externalRun, existingExternalRun.get()))
            throw new AmbiguousRunIdMappingException(String.format("Found different mapping for anonymized run id: %s" +
                            ". Existing mapping: %s. New mapping: %s. New value WON'T be stored", externalRun
                            .getAnonymizedId(),
                    existingExternalRun.get().getRealRunId(), externalRun.getRealRunId()));
    }

    private boolean isMappingTheSame(ExternalRun externalRun, ExternalRun existingExternalRun) {
        return Objects.equals(externalRun.getRealRunId(), existingExternalRun
                .getRealRunId());
    }

    @Override
    public List<ExternalRun> getAll() {
        List<ExternalRunEntity> externalRunEntities = externalRunRepository.findAll();
        return ExternalRunConverter.convert(externalRunEntities);
    }

    public class AmbiguousRunIdMappingException extends RuntimeException {
        public AmbiguousRunIdMappingException(String message) {
            super(message);
        }
    }
}
