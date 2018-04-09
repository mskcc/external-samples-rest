package org.mskcc.igo.pi.external.jpa.gateway;

import org.mskcc.domain.external.ExternalSample;
import org.mskcc.igo.pi.external.jpa.converter.ExternalSampleConverter;
import org.mskcc.igo.pi.external.jpa.entity.ExternalSampleEntity;
import org.mskcc.igo.pi.external.rest.sample.ExternalSampleGateway;
import org.mskcc.igo.pi.external.rest.sample.ExternalSampleRepository;

import java.util.List;
import java.util.Optional;

public class JPAExternalSampleGateway implements ExternalSampleGateway {
    private ExternalSampleRepository externalSampleRepository;

    public JPAExternalSampleGateway(ExternalSampleRepository externalSampleRepository) {
        this.externalSampleRepository = externalSampleRepository;
    }

    @Override
    public Optional<ExternalSample> getSample(String id) {
        ExternalSampleEntity externalSampleEntity = externalSampleRepository.findByExternalId(id);

        if(externalSampleEntity == null)
            return Optional.empty();

        return Optional.of(ExternalSampleConverter.convert(externalSampleEntity));
    }

    @Override
    public void addSample(ExternalSample externalSample) {
        ExternalSampleEntity externalSampleEntity = ExternalSampleConverter.convert(externalSample);
        externalSampleRepository.save(externalSampleEntity);
    }

    @Override
    public List<ExternalSample> getAll() {
        List<ExternalSampleEntity> externalSampleEntities = externalSampleRepository.findAll();
        return ExternalSampleConverter.convert(externalSampleEntities);
    }
}
