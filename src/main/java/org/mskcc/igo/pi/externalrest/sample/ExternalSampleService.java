package org.mskcc.igo.pi.externalrest.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mskcc.domain.sample.ExternalSample;

import java.util.List;

@Service
public class ExternalSampleService {
    @Autowired
    private ExternalSampleRepository externalSampleRepository;

    public List<ExternalSample> getSample(String id) {
        return externalSampleRepository.findByExternalId(id);
    }

    public ExternalSample addSample(ExternalSample externalSample) {
        return externalSampleRepository.save(externalSample);
    }

    public List<ExternalSample> getAll() {
        return (List<ExternalSample>) externalSampleRepository.findAll();
    }

    static class SampleExistsException extends RuntimeException {
        public SampleExistsException(String message) {
            super(message);
        }
    }
}
