package com.jakting.news.adapter;

public class Comment {
    private String id;
    private String author;
    private String comment_content;


    public Comment(String id, String author, String comment_content) {
        this.id = id;
        this.author = author;
        this.comment_content = comment_content;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getComment_content() {
        return comment_content;
    }
}
