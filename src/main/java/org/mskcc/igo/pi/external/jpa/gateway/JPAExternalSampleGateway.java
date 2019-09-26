package org.mskcc.igo.pi.external.jpa.gateway;

import org.apache.commons.lang3.StringUtils;
import org.mskcc.domain.external.ExternalSample;
import org.mskcc.igo.pi.external.jpa.converter.ExternalSampleConverter;
import org.mskcc.igo.pi.external.jpa.entity.ExternalSampleEntity;
import org.mskcc.igo.pi.external.rest.sample.ExternalSampleGateway;
import org.mskcc.igo.pi.external.rest.sample.ExternalSampleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    public Page<ExternalSample> findPaginated(int page, int size, String sort, String direction) {
        PageRequest pageable;

        if (!StringUtils.isEmpty(sort))
            pageable = new PageRequest(page, size, new Sort(Sort.Direction.fromStringOrNull(direction), sort));
        else
            pageable = new PageRequest(page, size);

        Page<ExternalSampleEntity> all = externalSampleRepository.findAll(pageable);

        return ExternalSampleConverter.convert(all);
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
