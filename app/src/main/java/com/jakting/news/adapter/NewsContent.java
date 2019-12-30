package com.jakting.news.adapter;

public class NewsContent {
    private String title;
    private String source;
    private String ptime;
    private String content;
    private String likecount;
    private String commentcount;


    public NewsContent(String title, String source, String ptime, String content, String likecount, String commentcount) {
        this.title = title;
        this.source = source;
        this.ptime = ptime;
        this.content = content;
        this.likecount = likecount;
        this.commentcount = commentcount;
    }


    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getPtime() {
        return ptime;
    }

    public String getContent() {
        return content;
    }

    public String getLikecount() {
        return likecount;
    }

    public String getCommentcount() {
        return commentcount;
    }
}
