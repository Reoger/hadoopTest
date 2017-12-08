package com.hut.bean;


import java.util.Date;

public class BeanForPaper {
    /**
     * paper_name : 我的论文
     * upload_time : 2017-05-25 11:22:33
     * update_time : 2017-05-25 11:22:55
     * down_link : http:www.google.com
     * size : 12
     * uploader : reoger
     * author : sb
     * abstract : hello word
     */
    private String paper_name;
    private Date upload_time;
    private Date update_time;
    private String down_link;
    private int size;
    private String uploader;
    private String author;
    private String abstractX;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public void setDown_link(String down_link) {
        this.down_link = down_link;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public String getDown_link() {
        return down_link;
    }

    public int getSize() {
        return size;
    }

    public String getUploader() {
        return uploader;
    }

    public String getAuthor() {
        return author;
    }

    public String getAbstractX() {
        return abstractX;
    }
}
