package org.mskcc.igo.pi.external.rest.sample;

import org.mskcc.domain.external.ExternalSample;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExternalSampleGateway {
    Optional<ExternalSample> getSample(String id);

    Collection<ExternalSample> getSamplesByCmoPatientId(String patientCmoId);

    Collection<ExternalSample> getSamplesByDmpPatientId(String patientDmpId);

    void addSample(ExternalSample externalSample);

    List<ExternalSample> getAll();
}
