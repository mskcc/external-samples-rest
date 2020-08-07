package org.mskcc.igo.pi.external.rest.sample;

import org.mskcc.domain.external.ExternalSample;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExternalSampleGateway {
    Optional<ExternalSample> getSample(String id);

    Page<ExternalSample> findPaginated(int page, int size, String sort, String direction);
    
    Collection<ExternalSample> getSamplesByCmoPatientId(String patientCmoId);

    Collection<ExternalSample> getSamplesByDmpPatientId(String patientDmpId);

    void addSample(ExternalSample externalSample);

    List<ExternalSample> getAll();
}
