package com.zfy.rxbestpractices.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author: fanyuzeng on 2018/3/6 10:30
 */
@Entity
public class LikeBean {

    @Id
    private Long id;

    private String guid;
    private String imageUrl;
    private String title;
    private String url;
    private int type;
    private long time;

    @Generated(hash = 1258777425)
    public LikeBean() {
    }

    @Generated(hash = 693919166)
    public LikeBean(Long id, String guid, String imageUrl, String title, String url,
            int type, long time) {
        this.id = id;
        this.guid = guid;
        this.imageUrl = imageUrl;
        this.title = title;
        this.url = url;
        this.type = type;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
