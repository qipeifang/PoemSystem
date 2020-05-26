package com.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_Poetry", schema = "test")
public class TPoetry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;//自增
    private String name;//古诗名
    private int dynastyid;
    private String content;
    private String annotation;
    private String note;
    private int typeid;
    private int authoruid;
    private String translation;

    public TPoetry() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDynastyid() {
        return dynastyid;
    }

    public void setDynastyid(int dynastyid) {
        this.dynastyid = dynastyid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getAuthoruid() {
        return authoruid;
    }

    public void setAuthoruid(int authoruid) {
        this.authoruid = authoruid;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
