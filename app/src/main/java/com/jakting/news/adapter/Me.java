package com.jakting.news.adapter;

public class Me {
    private String id;
    private String username;
    private String password;
    private String name;
    private String like_count;
    private String star_count;
    private String star;


    public Me(String id, String username, String password, String name, String like_count, String star_count, String star) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.like_count = like_count;
        this.star_count = star_count;
        this.star = star;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLike_count() {
        return like_count;
    }

    public String getStar_count() {
        return star_count;
    }

    public String getStar() {
        return star;
    }
}
