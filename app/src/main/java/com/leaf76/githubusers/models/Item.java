package com.leaf76.githubusers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    @SerializedName("html_url")
    @Expose
    private String html_url;



    public Item(String login,int id, String avatar_url, String html_url){
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
    }


    // Getter Methods

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    // Setter Methods

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

}
