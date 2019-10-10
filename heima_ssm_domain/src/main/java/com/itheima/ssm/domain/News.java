package com.itheima.ssm.domain;

import java.util.Date;

public class News {
    private String news_id;
    private String title;
    private String content;
    private String autor;
    private Date submitDate;
    private String submitDateStr;
    private Integer catalog_id;
    private String catalogStr;
    private String keywords;
    private Integer clickcount;
    private String cover_pic;
    private Integer status;
    private String statusStr;

    public News() {
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmitDateStr() {
        return submitDate.toLocaleString();
    }

    public Integer getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(Integer catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getCatalogStr() {
        return catalogStr;
    }

    public void setCatalogStr(String catalogStr) {
        this.catalogStr = catalogStr;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public String getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if(status == 0){
            statusStr = "未提交";
        }else if(status == 1){
            statusStr = "已提交，待主编审核";
        }else if(status == 3){
            statusStr = "主编退回";
        }else if(status == 4){
            statusStr = "主编审核通过，待总编审核";
        }else if(status == 5){
            statusStr = "总编退回";
        }else if(status == 6){
            statusStr = "总编审核通过，已发布";
        }

        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "News{" +
                "news_id='" + news_id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", autor='" + autor + '\'' +
                ", submitDate=" + submitDate +
                ", submitDateStr='" + submitDateStr + '\'' +
                ", catalog_id=" + catalog_id +
                ", catalogStr='" + catalogStr + '\'' +
                ", keywords='" + keywords + '\'' +
                ", clickcount=" + clickcount +
                ", cover_pic='" + cover_pic + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                '}';
    }
}
