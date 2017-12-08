package com.hut.bean;

public class DateBean {

    /**
     * down_link : http:172.22.66.245:9090/down
     * date_of_brith : 000-01-01
     * size : 10
     * author : reoger
     * name : android 开发
     * key_word : oo
     * press : 清华大学出版社
     * content : 这里就有很多很多的数据
     */
    private String down_link;
    private String date_of_brith;
    private int size;
    private String author;
    private String name;
    private String key_word;
    private String press;
    private String content;

    public void setDown_link(String down_link) {
        this.down_link = down_link;
    }

    public void setDate_of_brith(String date_of_brith) {
        this.date_of_brith = date_of_brith;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDown_link() {
        return down_link;
    }

    public String getDate_of_brith() {
        return date_of_brith;
    }

    public int getSize() {
        return size;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getKey_word() {
        return key_word;
    }

    public String getPress() {
        return press;
    }

    public String getContent() {
        return content;
    }
}
