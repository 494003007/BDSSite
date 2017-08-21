package com.bdssite.modules.usermanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 收藏
 * Created by keben on 2017-03-21.
 */
@Entity
public class  CollectionProfession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue
    private Long id;


    //关注职业
    @Column(unique=true)
    private String profession;
    //网页地址
    @Column(unique=true)
    private String url;
    //关注公司
    @Column(unique=true)
    private String concernCompany;
//
//    用户 -- 收藏关系：多对多关系;
    @ManyToMany
    @JoinTable(name="SysCollectionUser",joinColumns={@JoinColumn(name="collectionId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserInfo> userInfos;

    public List<UserInfo> getUser() {
        return userInfos;
    }

    public void setUser(List<UserInfo> user) {
        this.userInfos = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConcernCompany() {
        return concernCompany;
    }

    public void setConcernCompany(String concernCompany) {
        this.concernCompany = concernCompany;
    }
}
