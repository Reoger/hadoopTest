package com.hut.bean;

import java.util.Date;

public class BeanForDoc {

    /**
     * organization : 湖工大
     * name : this is a test.png
     * key_word : 政
     * time : 2017
     * integer : 111
     * content : 类别：书名、出版社、作者、关键词、出版日期、产品编号、下载链接、大小；
     * down_link: www.baidu.com
     */
    private String organization;
    private String name;
    private String key_word;
    private int time;
    private int integer;
    private String content;
    private Date upload_time;

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public String getDown_link() {
        return down_link;
    }

    public void setDown_link(String down_link) {
        this.down_link = down_link;
    }

    private String down_link;

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrganization() {
        return organization;
    }

    public String getName() {
        return name;
    }

    public String getKey_word() {
        return key_word;
    }

    public int getTime() {
        return time;
    }

    public int getInteger() {
        return integer;
    }

    public String getContent() {
        return content;
    }
}
