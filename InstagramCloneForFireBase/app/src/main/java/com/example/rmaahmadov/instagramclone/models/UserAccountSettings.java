package com.example.rmaahmadov.instagramclone.models;

import android.media.Image;

import com.example.rmaahmadov.instagramclone.utils.UniversalImageLoader;


public class UserAccountSettings {
    private String description = "";
    private String display_name = "";
    private long followers = 0;
    private long following = 0;
    private long post = 0;
    private String username;
    private String web = "";


    public UserAccountSettings(String description, String display_name, long followers, long following, long post, String username, String web) {
        this.description = description;
        this.display_name = display_name;
        this.followers = followers;
        this.following = following;
        this.post = post;
        this.username = username;
        this.web = web;
    }


    public UserAccountSettings() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getPost() {
        return post;
    }

    public void setPost(long post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
