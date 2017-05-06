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
    private String parentNode;//父节点

    private String classification;//职业
    private String description;//职业描述
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
