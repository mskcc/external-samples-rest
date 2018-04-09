package org.mskcc.igo.pi.external.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "external_sample")
public class ExternalSampleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "run_id")
    private String runId;

    @Column(nullable = false, name = "bam_path")
    private String bamPath;

    @Column(nullable = false, name = "sample_origin")
    private String sampleOrigin;

    @Column(name = "specimen_type")
    private String specimenType;

    @Column(name = "nucleid_acid")
    private String nucleidAcid;

    @Column(name = "cmo_id")
    private String cmoId;

    @Column(nullable = false, name = "external_id", unique = true)
    private String externalId;

    @Column(name = "patient_cmo_id")
    private String patientCmoId;

    @Column(nullable = false, name = "patient_external_id")
    private String patientExternalId;

    @Column(
            nullable = false,
            name = "tumor_normal"
    )
    private String tumorNormal;

    @Column(
            nullable = false,
            name = "sample_class"
    )
    private String sampleClass;
    @Column(
            nullable = false
    )
    private int counter;

    public ExternalSampleEntity(String runId, String bamPath, String externalId, String patientExternalId) {
        this.runId = runId;
        this.bamPath = bamPath;
        this.externalId = externalId;
        this.patientExternalId = patientExternalId;
    }

    // defualt constructor for JPA
    protected ExternalSampleEntity() {
    }

    public String getRunId() {
        return this.runId;
    }

    public void setRunId(String runID) {
        this.runId = runID;
    }

    public String getBamPath() {
        return this.bamPath;
    }

    public void setBamPath(String bamPath) {
        this.bamPath = bamPath;
    }

    public String getSampleOrigin() {
        return this.sampleOrigin;
    }

    public void setSampleOrigin(String sampleOrigin) {
        this.sampleOrigin = sampleOrigin;
    }

    public String getSpecimenType() {
        return this.specimenType;
    }

    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }

    public String getNucleidAcid() {
        return this.nucleidAcid;
    }

    public void setNucleidAcid(String nucleidAcid) {
        this.nucleidAcid = nucleidAcid;
    }

    public String getCmoId() {
        return this.cmoId;
    }

    public void setCmoId(String cmoId) {
        this.cmoId = cmoId;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getPatientCmoId() {
        return this.patientCmoId;
    }

    public void setPatientCmoId(String patientCmoId) {
        this.patientCmoId = patientCmoId;
    }

    public String getPatientExternalId() {
        return this.patientExternalId;
    }

    public void setPatientExternalId(String patientExternalId) {
        this.patientExternalId = patientExternalId;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExternalSample{" +
                "id=" + id +
                ", run='" + runId + '\'' +
                ", origin='" + sampleOrigin + '\'' +
                ", specimenType='" + specimenType + '\'' +
                ", nucleidAcid='" + nucleidAcid + '\'' +
                ", cmoId='" + cmoId + '\'' +
                ", externalId='" + externalId + '\'' +
                ", patientCmoId='" + patientCmoId + '\'' +
                ", patientDmpId='" + patientExternalId + '\'' +
                '}';
    }

    public String getTumorNormal() {
        return tumorNormal;
    }

    public void setTumorNormal(String tumorNormal) {
        this.tumorNormal = tumorNormal;
    }

    public String getSampleClass() {
        return sampleClass;
    }

    public void setSampleClass(String sampleClass) {
        this.sampleClass = sampleClass;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

