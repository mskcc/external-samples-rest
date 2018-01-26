package org.mskcc.igo.pi.externalrest.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.mskcc.domain.sample.ExternalSample;

import java.util.List;

public interface ExternalSampleRepository extends JpaRepository<ExternalSample, Long> {
    List<ExternalSample> findByExternalId(String externalId);
}
