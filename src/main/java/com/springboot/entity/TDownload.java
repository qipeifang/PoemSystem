package com.springboot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Download", schema = "test")
public class TDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;//自增
    private int poetryid;//诗词id
    private String email;//下载用户email
    private Date downloadtime;//下载时间

    public TDownload() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoetryid() {
        return poetryid;
    }

    public void setPoetryid(int poetryid) {
        this.poetryid = poetryid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDownloadtime() {
        return downloadtime;
    }

    public void setDownloadtime(Date downloadtime) {
        this.downloadtime = downloadtime;
    }
}
