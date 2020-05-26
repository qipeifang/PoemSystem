package com.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Comment", schema = "test")
public class TComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;//自增
    private String email;//评论者邮箱
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;//发表时间
    private String comments;//评论内容
    private long poetryid;//诗词类id
    private String poetryname;
    public TComment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getPoetryid() {
        return poetryid;
    }

    public void setPoetryid(long poetryid) {
        this.poetryid = poetryid;
    }
    public String getPoetryname() {
        return poetryname;
    }

    public void setPoetryname(String poetryname) {
        this.poetryname = poetryname;
    }
}
