package com.bdssite.modules.usermanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by keben on 2017-04-02.
 */
@Entity
public class HrReleaseRecruit implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    public Long id;


    @ManyToOne(fetch = FetchType.EAGER)//设置在“一方”pojo的外键字段上
    @JoinColumn(name = "userId", referencedColumnName = "uid")//设置对应数据表的列名和引用的数据表的列名
    private UserInfo user;

    //公司名
    public String companyName;
    //公司领域
    public String companyField;
    //公司规模或人
    public int companyScale;
    //公司创建时间
    public Date companyCreateTime;
    //公司福利
    public String tag;
    //招聘职位
    public String profession;
    //学位要求
    public String univercity;
    //工作经验
    public int workExperience;
    //工作地点
    public String workingAddress;
    //年薪
    public String yearlySalary;
    //发布时间
    public Date releaseTime;
    //反馈截止时间
    public Date feedbackTime;
    //公司类型
    public String companyType;
    //企业描述/自我
    public String selfDescribe;
    //公司职责
    public String workDuty;
    //岗位要求
    public String workRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyField() {
        return companyField;
    }

    public void setCompanyField(String companyField) {
        this.companyField = companyField;
    }

    public int getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(int companyScale) {
        this.companyScale = companyScale;
    }

    public Date getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Date companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUnivercity() {
        return univercity;
    }

    public void setUnivercity(String univercity) {
        this.univercity = univercity;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String getWorkingAddress() {
        return workingAddress;
    }

    public void setWorkingAddress(String workingAddress) {
        this.workingAddress = workingAddress;
    }

    public String getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(String yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getSelfDescribe() {
        return selfDescribe;
    }

    public void setSelfDescribe(String selfDescribe) {
        this.selfDescribe = selfDescribe;
    }

    public String getWorkDuty() {
        return workDuty;
    }

    public void setWorkDuty(String workDuty) {
        this.workDuty = workDuty;
    }

    public String getWorkRequest() {
        return workRequest;
    }

    public void setWorkRequest(String workRequest) {
        this.workRequest = workRequest;
    }
}
