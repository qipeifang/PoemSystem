package com.springboot.entity;

import javax.persistence.*;
import javax.persistence.NamedStoredProcedureQuery;

/**
 *  @Procedure存储过程查询方法
 * 存储过程使用了注解@NamedStoredProcedureQuery 并绑定到一个JPA表。
 * procedureName是数据库中存储过程的名字
 * name是JPA中存储过程的名字
 * 使用注解@StoredProcedureParameter来定义存储过程使用的IN/OU参数
 */
@Entity
@NamedStoredProcedureQuery(name = "recommend",procedureName = "recommend",
        parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,name = "arg",type=Integer.class)})
@Table(name="recommend")
public class PRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long poetryid;//被收藏诗词id
    private int num;//收藏数量
    private String name;//古诗名
    private int typeid;
    private int authoruid;
    private int dynastyid;
    private String content;
    private String dynastyname;
    private String poetname;
    private String type;

    public long getPoetryid() {
        return poetryid;
    }

    public void setPoetryid(long poetryid) {
        this.poetryid = poetryid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDynastyname() {
        return dynastyname;
    }

    public void setDynastyname(String dynastyname) {
        this.dynastyname = dynastyname;
    }

    public String getPoetname() {
        return poetname;
    }

    public void setPoetname(String poetname) {
        this.poetname = poetname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
