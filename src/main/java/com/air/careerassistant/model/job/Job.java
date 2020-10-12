package com.air.careerassistant.model.job;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Calendar;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long localId;
    String jobUrl; //required
    String company; //required
    String company_url = null;
    String title; //required
    String location; //required
    String description = null;
    Date createdAt;
    String type;

    public Job() {
    }

    public Job(String jobUrl, String company, String company_url, String title, String location, String description, String type) {
        this.jobUrl = jobUrl;
        this.company = company;
        this.company_url = company_url;
        this.title = title;
        this.location = location;
        this.description = description;
        this.createdAt = new Date(Calendar.getInstance().getTime().getTime());
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return localId;
    }

    public String getJobUrl() {
        return jobUrl;
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
                ", jobUrl='" + jobUrl + '\'' +
                ", companyName='" + company + '\'' +
                ", companyUrl='" + company_url + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
