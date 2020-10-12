package com.air.careerassistant.model.job;


import com.air.careerassistant.model.post.Post;
import com.air.careerassistant.model.user.ApplicationUser;



import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.user.ApplicationUser;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long localId;
    String url; //required
    String company; //required
    public String companyUrl = null;
    String title; //required
    String location; //required
    String description = null;
    Date createdAt;
    String type;


    @OneToOne
    @JoinColumn(name="status_id", referencedColumnName = "id")
    public JobStatus jobStatus;

    @ManyToOne
    ApplicationUser applicationUser;


    @OneToMany(mappedBy = "job")
    List<Post> postList = new ArrayList<>();

    public Job() {
    }


    public Job(ApplicationUser applicationUser, String url, String company, String company_url, String title, String location, String description, String type, JobStatus jobStatus) {
        this.url = url;
        this.company = company;
        this.companyUrl = company_url;
        this.title = title;
        this.location = location;
        this.description = description;
        this.createdAt = new Date(Calendar.getInstance().getTime().getTime());
        this.type = type;
        this.jobStatus = jobStatus;
        this.applicationUser = applicationUser;

    }

    public JobStatus getJobStatus() {
        return jobStatus;
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
        return companyUrl;
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

    public String getType() {
        return type;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    @Override
    public String toString() {
        return "Job{" +
                "localId=" + localId +
                ", url='" + url + '\'' +
                ", company='" + company + '\'' +
                ", company_url='" + companyUrl + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", type='" + type + '\'' +
                '}';
    }

    public List<Post> getPostList() {
        return postList;
    }
}
