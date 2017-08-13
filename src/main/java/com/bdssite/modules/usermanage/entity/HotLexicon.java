package com.bdssite.modules.usermanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 热词
 * Created by keben on 2017-03-21.
 */
@Entity
public class HotLexicon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id@GeneratedValue
    public int id;

    //词
    @Column(unique=true)
    private String word;
    //被搜索的次数
    private int searchTime;
    //描述
    private String description;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(int searchTime) {
        this.searchTime = searchTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
