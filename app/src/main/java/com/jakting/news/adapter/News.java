package com.jakting.news.adapter;

public class News {
    private String source;
    private String title;
    private String url;
    private String digest;
    private String imgsrc;
    private String ptime;


    public News(String source, String title, String url, String digest, String imgsrc, String ptime) {
        this.source = source;
        this.title = title;
        this.url = url;
        this.digest = digest;
        this.imgsrc = imgsrc;
        this.ptime = ptime;
    }


    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDigest() {
        return digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getPtime() {
        return ptime;
    }
}
