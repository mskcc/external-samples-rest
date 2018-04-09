package org.mskcc.igo.pi.external.rest.run;

import org.mskcc.igo.pi.external.jpa.entity.ExternalRunEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExternalRunRepository extends JpaRepository<ExternalRunEntity, Long> {
    Optional<ExternalRunEntity> findByAnonymizedRunId(String anonymizedRunId);
}
