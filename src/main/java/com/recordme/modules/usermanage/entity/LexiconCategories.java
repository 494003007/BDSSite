package com.recordme.modules.usermanage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by keben on 2017-04-02.
 */
@Entity
public class LexiconCategories implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;//主键.
    private String parentNod;//父节点
    private String node;//当前节点
    private String profession;//职业
    private String description;//职业描述

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParentNod() {
        return parentNod;
    }

    public void setParentNod(String parentNod) {
        this.parentNod = parentNod;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
