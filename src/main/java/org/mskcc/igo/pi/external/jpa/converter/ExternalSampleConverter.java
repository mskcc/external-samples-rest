package org.mskcc.igo.pi.external.jpa.converter;

import org.mskcc.domain.external.ExternalSample;
import org.mskcc.igo.pi.external.jpa.entity.ExternalSampleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ExternalSampleConverter {
    public static List<ExternalSample> convert(List<ExternalSampleEntity> externalSampleEntities) {
        List<ExternalSample> externalSamples = externalSampleEntities.stream()
                .map(ese -> convert(ese))
                .collect(Collectors.toList());

        return externalSamples;
    }

    public static ExternalSample convert(ExternalSampleEntity externalSampleEntity) {
        ExternalSample externalSample = new ExternalSample(
                externalSampleEntity.getCounter(),
                externalSampleEntity.getExternalId(),
                externalSampleEntity.getPatientExternalId(),
                externalSampleEntity.getBamPath(),
                externalSampleEntity.getRunId(),
                externalSampleEntity.getSampleClass(),
                externalSampleEntity.getSampleOrigin(),
                externalSampleEntity.getTumorNormal()
                );

        externalSample.setBaitVersion(externalSampleEntity.getBaitVersion());
        externalSample.setCmoId(externalSampleEntity.getCmoId());
        externalSample.setCounter(externalSampleEntity.getCounter());
        externalSample.setNucleidAcid(externalSampleEntity.getNucleidAcid());
        externalSample.setOncotreeCode(externalSampleEntity.getOncotreeCode());
        externalSample.setPatientCmoId(externalSampleEntity.getPatientCmoId());
        externalSample.setPreservationType(externalSampleEntity.getPreservationType());
        externalSample.setSex(externalSampleEntity.getSex());
        externalSample.setSpecimenType(externalSampleEntity.getSpecimenType());
        externalSample.setTissueSite(externalSampleEntity.getTissueSite());

        return externalSample;
    }

    public static ExternalSampleEntity convert(ExternalSample externalSample) {
        ExternalSampleEntity externalSampleEntity = new ExternalSampleEntity(
                externalSample.getExternalRunId(),
                externalSample.getFilePath(),
                externalSample.getExternalId(),
                externalSample.getExternalPatientId()
        );

        externalSampleEntity.setBaitVersion(externalSample.getBaitVersion());
        externalSampleEntity.setCmoId(externalSample.getCmoId());
        externalSampleEntity.setCounter(externalSample.getCounter());
        externalSampleEntity.setNucleidAcid(externalSample.getNucleidAcid());
        externalSampleEntity.setOncotreeCode(externalSample.getOncotreeCode());
        externalSampleEntity.setSampleOrigin(externalSample.getSampleOrigin());
        externalSampleEntity.setPatientCmoId(externalSample.getPatientCmoId());
        externalSampleEntity.setPreservationType(externalSample.getPreservationType());
        externalSampleEntity.setRunId(externalSample.getRunId());
        externalSampleEntity.setSex(externalSample.getSex());
        externalSampleEntity.setSpecimenType(externalSample.getSpecimenType());
        externalSampleEntity.setSampleClass(externalSample.getSampleClass());
        externalSampleEntity.setTissueSite(externalSample.getTissueSite());
        externalSampleEntity.setTumorNormal(externalSample.getTumorNormal());

        return externalSampleEntity;
    }
}
