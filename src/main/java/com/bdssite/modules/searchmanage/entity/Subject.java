package com.bdssite.modules.searchmanage.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程表
 * Created by D.
 */
@Entity
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id@GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)//设置在“一方”pojo的外键字段上
    @JoinColumn(name = "major", referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名
    private Major major;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
