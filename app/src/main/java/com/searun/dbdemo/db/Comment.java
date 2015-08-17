package com.searun.dbdemo.db;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by 陈玉柱 on 2015/8/7.
 */
public class Comment extends DataSupport{
    private int id;
    private String content;
    private Date publishDate;
    private News news;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
