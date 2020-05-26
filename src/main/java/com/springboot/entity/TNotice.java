package com.springboot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Notice", schema = "test")
public class TNotice {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;//自增
    private String email;//管理员email
    private String content;//通知内容
    private Date time;//创建时间
//    private boolean status;//状态 是否已读

    public TNotice() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

//    public boolean isStatus() {
//        return status;
//    }

//    public void setStatus(boolean status) {
//        this.status = status;
//    }
}
