package com.air.careerassistant.model.jobTrack;

import javax.persistence.Entity;

@Entity
public class JobStatus {
    private String status;

    public JobStatus() {
        this.status = "viewed";
    }

    public JobStatus(String status) {
        this.status = status;
    }
}
