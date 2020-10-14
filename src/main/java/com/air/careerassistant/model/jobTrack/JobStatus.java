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
    private String classString = "p-3 mb-2 bg-light text-dark";

    @OneToOne(mappedBy = "jobStatus")
    public Job job;

    public JobStatus(){}


    public JobStatus(String status, Job job) {
        this.job = job;
        this.status = status;
        this.classString = classString;
    }

    public long getId() {
        return id;
    }

    public String getClassString() {
        return classString;
    }

    public Job getJob() {
        return job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;

        switch (status) {
            case "viewed":
                this.classString = "p-3 mb-2 bg-light text-dark";
                break;
            case "applied":
                this.classString = "p-3 mb-2 bg-info text-white";
                break;
            case "contacted":
                this.classString = "p-3 mb-2 bg-secondary text-white";
                break;
            case "interview":
                this.classString = "p-3 mb-2 bg-warning text-dark";
                System.out.println("in the interview" + this.classString);
                break;
            case "offer":
                this.classString = "p-3 mb-2 bg-success text-white";
                break;
            case "accepted":
                this.classString = "p-3 mb-2 bg-success text-white";
                break;
            case "rejected":
                this.classString = "p-3 mb-2 bg-danger text-white";
                break;
        }
        System.out.println("this is the status "+this.status + "this is class "+this.classString);

    }
    @Override
    public String toString() {
        return "JobStatus{" +
                "status='" + status + '\'' +
                ", job=" + job +
                '}';
    }
}
