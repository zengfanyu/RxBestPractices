package com.zfy.rxbestpractices.http.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 由Jsoup解析出的国家地理中文网的实体文件
 * @author: fanyuzeng on 2018/3/13 14:40
 */
public class NGBean {
    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("content")
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NGBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
