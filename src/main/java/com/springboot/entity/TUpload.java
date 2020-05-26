package com.springboot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Upload", schema = "test")
public class TUpload {
    //上传资源表
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;//自增
    private String uploadtext;//上传资源内容
    private String useremail;//上传用户邮箱
    private String adminemail;//审核该上传资源的管理员email
    private Date uploadtime;//上传时间
    private boolean status;//上传资源状态 false:审核未通过 true：审核已通过

    public TUpload() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUploadtext() {
        return uploadtext;
    }

    public void setUploadtext(String uploadtext) {
        this.uploadtext = uploadtext;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
