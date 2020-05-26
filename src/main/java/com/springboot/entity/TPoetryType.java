package com.springboot.entity;

import javax.persistence.*;
@Entity
//诗词类别表
@Table(name = "T_Type", schema = "test")
public class TPoetryType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;//自增
    private String type;//这个实体类用于一开始进入界面的时候可以从后台取数据foreach到前台数据库存在的所有类型的诗歌

    public TPoetryType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}