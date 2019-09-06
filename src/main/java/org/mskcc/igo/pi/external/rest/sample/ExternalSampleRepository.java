package org.mskcc.igo.pi.external.rest.sample;

import org.mskcc.igo.pi.external.jpa.entity.ExternalSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface ExternalSampleRepository extends JpaRepository<ExternalSampleEntity, Long> {
    ExternalSampleEntity findByExternalId(String externalId);

    Collection<ExternalSampleEntity> findByPatientCmoId(String patientCmoId);

    Collection<ExternalSampleEntity> findByPatientExternalId(String patientDmpId);
}
