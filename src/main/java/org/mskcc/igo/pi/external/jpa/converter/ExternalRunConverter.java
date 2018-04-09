package org.mskcc.igo.pi.external.jpa.converter;

import com.google.common.base.Preconditions;
import org.mskcc.domain.external.ExternalRun;
import org.mskcc.igo.pi.external.jpa.entity.ExternalRunEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ExternalRunConverter {
    public static List<ExternalRun> convert(List<ExternalRunEntity> externalRunEntities) {
        List<ExternalRun> externalRuns = externalRunEntities.stream()
                .map(ere -> convert(ere))
                .collect(Collectors.toList());

        return externalRuns;
    }

    public static ExternalRun convert(ExternalRunEntity externalRunEntity) {
        Preconditions.checkNotNull(externalRunEntity);

        ExternalRun externalRun = new ExternalRun(externalRunEntity.getRunID(), externalRunEntity.getAnonymizedRunId());

        return externalRun;
    }

    public static ExternalRunEntity convert(ExternalRun externalRun) {
        Preconditions.checkNotNull(externalRun);

        ExternalRunEntity externalRunEntity = new ExternalRunEntity(externalRun.getAnonymizedId(), externalRun
                .getRealRunId());

        return externalRunEntity;
    }
}
