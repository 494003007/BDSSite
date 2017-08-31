package com.bdssite.modules.searchmanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 课程表
 * Created by D.
 */
@Entity
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id@GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MajorToSubject",joinColumns = {@JoinColumn(name = "sid",referencedColumnName="id")},inverseJoinColumns = {@JoinColumn(name = "mid",referencedColumnName="id")})
    private List<Major> majors;

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

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }
}
