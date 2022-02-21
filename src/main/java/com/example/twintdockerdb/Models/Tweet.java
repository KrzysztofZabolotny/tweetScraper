package com.example.twintdockerdb.Models;

import javax.persistence.*;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String searchedByHashtag;
    String internalTweetId;
    String postDate;
    String postTime;
    String timeZone;
    String username;
    @Column(columnDefinition ="varchar(500)")
    String content;

    public Tweet() {
    }

    public Tweet(String searchedByHashtag, String internalTweetId, String postDate, String postTime, String timeZone, String username, String content) {
        this.searchedByHashtag = searchedByHashtag;
        this.internalTweetId = internalTweetId;
        this.postDate = postDate;
        this.postTime = postTime;
        this.timeZone = timeZone;
        this.username = username;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchedByHashtag() {
        return searchedByHashtag;
    }

    public void setSearchedByHashtag(String searchedByHashtag) {
        this.searchedByHashtag = searchedByHashtag;
    }

    public String getInternalTweetId() {
        return internalTweetId;
    }

    public void setInternalTweetId(String internalTweetId) {
        this.internalTweetId = internalTweetId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
