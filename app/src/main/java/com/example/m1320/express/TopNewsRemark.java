package com.example.m1320.express;

import java.io.Serializable;

/**
 * Created by m1320 on 2016/8/4.
 */
public class TopNewsRemark implements Serializable{
    private String title;
    private String datetime;
    private String author;
    private String img1;
    private String img2;
    private String img3;
    private String url;

    public void Remark(){

    }

    public TopNewsRemark(String title, String datetime, String author, String img1, String url) {
        this.title = title;
        this.datetime = datetime;
        this.author = author;
        this.img1 = img1;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
