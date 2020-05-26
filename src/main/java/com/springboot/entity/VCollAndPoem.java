package com.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VCollAndPoem", schema = "test")
public class VCollAndPoem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String email;//收藏者邮箱
    private int poetryid;//被收藏诗词id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;//收藏时间
    private String poetryname;//古诗名
    private int typeid;
    private String type;//类别
    private int authoruid;
    private String poetname;//作者名


    public VCollAndPoem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoetryid() {
        return poetryid;
    }

    public void setPoetryid(int poetryid) {
        this.poetryid = poetryid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPoetryname() {
        return poetryname;
    }

    public void setPoetryname(String poetryname) {
        this.poetryname = poetryname;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAuthoruid() {
        return authoruid;
    }

    public void setAuthoruid(int authoruid) {
        this.authoruid = authoruid;
    }

    public String getPoetname() {
        return poetname;
    }

    public void setPoetname(String poetname) {
        this.poetname = poetname;
    }
}
