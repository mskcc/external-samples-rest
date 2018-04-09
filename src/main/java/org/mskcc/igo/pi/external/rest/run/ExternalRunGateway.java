package org.mskcc.igo.pi.external.rest.run;

import org.mskcc.domain.external.ExternalRun;

import java.util.List;
import java.util.Optional;

public interface ExternalRunGateway {
    Optional<ExternalRun> getRunByAnonymizedId(String anonymizedId);

    void addRun(ExternalRun externalRun);

    List<ExternalRun> getAll();
}
