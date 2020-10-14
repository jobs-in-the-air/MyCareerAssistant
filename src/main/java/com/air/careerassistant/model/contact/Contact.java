package com.air.careerassistant.model.contact;

import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.user.ApplicationUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String linkedinUrl;
    String name;
    String misc = "";

    @ManyToOne
    ApplicationUser contactUser;

    @ManyToMany(mappedBy = "relatedContacts")
    Set<Job> relatedJobs = new HashSet<>();

    public Contact(){}

    public Contact(String linkedinUrl, String name, ApplicationUser contactUser) {
        this.linkedinUrl = linkedinUrl;
        this.name = name;
        this.contactUser = contactUser;
    }

    public Long getId() {
        return id;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public String getName() {
        return name;
    }

    public String getMisc() {
        return misc;
    }

    public ApplicationUser getContactUser() {
        return contactUser;
    }

    public Set<Job> getRelatedJobs() {
        return relatedJobs;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }
    public void addJob(Job job){
        relatedJobs.add(job);
    }

    public void removeJob(Job job) {
        relatedJobs.remove(job);
    }
}
