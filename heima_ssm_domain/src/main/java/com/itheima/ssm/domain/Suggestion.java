package com.itheima.ssm.domain;

import java.util.Date;

/**
 * 修改意见实体类
 */
public class Suggestion {
    private String sug_id;
    private String content;
    private String username;
    private Date submitdate;
    private String submitdateStr;
    private String news_id;

    public String getSug_id() {
        return sug_id;
    }

    public void setSug_id(String sug_id) {
        this.sug_id = sug_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public String getSubmitdateStr() {
        return submitdate.toLocaleString();
    }

    public void setSubmitdateStr(String submitdateStr) {
        this.submitdateStr = submitdateStr;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
}
