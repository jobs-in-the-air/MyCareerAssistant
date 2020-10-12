package com.air.careerassistant.model.post;

import com.air.careerassistant.model.job.Job;
import sun.jvm.hotspot.gc.shared.Generation;

import javax.persistence.*;

import com.air.careerassistant.model.user.ApplicationUser;

import java.sql.Date;
import java.util.Calendar;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Job job;
    @ManyToOne
    ApplicationUser postUser;
    String body;
    Date createdAt;

    public Post(Job job, ApplicationUser postUser, String body) {
        this.job = job;
        this.postUser = postUser;
        this.body = body;
        this.createdAt = new Date(Calendar.getInstance().getTime().getTime());
    }

    public Post(){}


}


