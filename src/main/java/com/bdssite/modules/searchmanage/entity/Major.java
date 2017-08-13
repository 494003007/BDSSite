package com.bdssite.modules.searchmanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 专业表
 * Created by D.
 */

@Entity
public class Major  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    //专业名
    @Column(unique=true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "major")//指向多的那方的pojo的关联外键字段
    private List<Subject> subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
