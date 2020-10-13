package com.air.careerassistant.model.jobTrack;

import com.air.careerassistant.model.job.Job;

import javax.persistence.*;

@Entity
@Table(name = "jobStatus")
public class JobStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String status = "viewed";

    @OneToOne(mappedBy = "jobStatus")
    public Job job;

    public JobStatus(){}


    public JobStatus(String status, Job job) {
        this.job = job;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Job getJob() {
        return job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JobStatus{" +
                "status='" + status + '\'' +
                ", job=" + job +
                '}';
    }
}
