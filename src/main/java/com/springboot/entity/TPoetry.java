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
    private long dynastyid;
    private String content;//诗词内容
    private String annotation;//注释
    private long typeid;
    private long authoruid;
    private String translation;//翻译

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

    public long getDynastyid() {
        return dynastyid;
    }

    public void setDynastyid(long dynastyid) {
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

    public long getTypeid() {
        return typeid;
    }

    public void setTypeid(long typeid) {
        this.typeid = typeid;
    }

    public long getAuthoruid() {
        return authoruid;
    }

    public void setAuthoruid(long authoruid) {
        this.authoruid = authoruid;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
