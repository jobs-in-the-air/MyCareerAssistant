package com.air.careerassistant.model.job;


import com.air.careerassistant.model.contact.Contact;
import com.air.careerassistant.model.post.Post;
import com.air.careerassistant.model.user.ApplicationUser;



import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.user.ApplicationUser;
import javax.persistence.*;
import java.sql.Date;
import java.util.*;

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
    String type =null;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="status_id", referencedColumnName = "id")
    public JobStatus jobStatus;

    @ManyToOne
    ApplicationUser applicationUser;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    public List<Post> postList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "joinContacts",
        joinColumns = @JoinColumn(name="relatedJobs"),
        inverseJoinColumns = @JoinColumn(name="relatedContacts")
    )
    Set<Contact> relatedContacts = new HashSet<>();
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

    public Set<Contact> getRelatedContacts() {
        return relatedContacts;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void addContact(Contact contact) {
        relatedContacts.add(contact);
    }

    public void removeContact(Contact contact) {
        relatedContacts.remove(contact);
    }
}
