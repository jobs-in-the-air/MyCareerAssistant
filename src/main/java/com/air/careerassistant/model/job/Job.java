package com.air.careerassistant.model.job;

import com.air.careerassistant.model.jobTrack.JobStatus;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long localId;
    String url; //required
    String company; //required
    String company_url = null;
    String title; //required
    String location; //required
    String description = null;
    Date createdAt;
    String type;

    @OneToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    public JobStatus jobStatus;

    public Job() {
    }

    public Job(String url, String company, String company_url, String title, String location, String description, String type, JobStatus jobStatus) {
        this.url = url;
        this.company = company;
        this.company_url = company_url;
        this.title = title;
        this.location = location;
        this.description = description;
        this.createdAt = new Date(Calendar.getInstance().getTime().getTime());
        this.type = type;
        this.jobStatus = jobStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return localId;
    }

    public String getUrl() {
        return url;
    }

    public String getCompanyName() {
        return company;
    }

    public String getCompanyUrl() {
        return company_url;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "localId=" + localId +
                ", url='" + url + '\'' +
                ", company='" + company + '\'' +
                ", company_url='" + company_url + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", type='" + type + '\'' +
                '}';
    }
}
