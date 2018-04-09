package org.mskcc.igo.pi.external.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "external_run")
public class ExternalRunEntity{
    @Id
    @Column(nullable = false, name = "anonymized_run_id")
    private String anonymizedRunId;

    @Column(nullable = false, name = "real_run_id")
    private String runID;

    public ExternalRunEntity(String anonymizedRunId, String runID) {
        this.anonymizedRunId = anonymizedRunId;
        this.runID = runID;
    }

    // defualt constructor for JPA
    protected ExternalRunEntity() {}

    @Override
    public String toString() {
        return "ExternalRunEntity{" +
                ", anonymizedRunID='" + anonymizedRunId + '\'' +
                ", runID='" + runID + '\'' +
                '}';
    }

    public String getAnonymizedRunId() {
        return anonymizedRunId;
    }

    public void setAnonymizedRunId(String anonymizedRunId) {
        this.anonymizedRunId = anonymizedRunId;
    }

    public String getRunID() {
        return runID;
    }

    public void setRunID(String runID) {
        this.runID = runID;
    }
}
