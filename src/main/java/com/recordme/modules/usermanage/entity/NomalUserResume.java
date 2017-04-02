package com.recordme.modules.usermanage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by keben on 2017-04-02.
 */
@Entity
public class NomalUserResume implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    public int id;
    //工作过的公司
    public String workedCompany;
    //学位
    public String degree;
    //毕业学校
    public String school;
    //工作经验
    public int workExperience;
    //奖项
    public String prize;
    //自我描述
    public String describeself;
    //爱好
    public String hobby;
    //期望工作地点
    public String hopeWorkingAddress;
    //期望年薪
    public String hopeYearlySalary;
    //现在工作地点
    public String nowWorkingAddress;
    //现在年薪
    public String nowYearlySalary;
    //工作具体职责
    public String workDuty;
    //所学专业
    public String major;
    //职位
    public String profession;
    //进入大学时间
    public Date startUnivercityTime;
    //毕业时间
    public Date graduateTime;
    //是否兼职
    public int partTimeJob;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkedCompany() {
        return workedCompany;
    }

    public void setWorkedCompany(String workedCompany) {
        this.workedCompany = workedCompany;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDescribeself() {
        return describeself;
    }

    public void setDescribeself(String describeself) {
        this.describeself = describeself;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHopeWorkingAddress() {
        return hopeWorkingAddress;
    }

    public void setHopeWorkingAddress(String hopeWorkingAddress) {
        this.hopeWorkingAddress = hopeWorkingAddress;
    }

    public String getHopeYearlySalary() {
        return hopeYearlySalary;
    }

    public void setHopeYearlySalary(String hopeYearlySalary) {
        this.hopeYearlySalary = hopeYearlySalary;
    }

    public String getNowWorkingAddress() {
        return nowWorkingAddress;
    }

    public void setNowWorkingAddress(String nowWorkingAddress) {
        this.nowWorkingAddress = nowWorkingAddress;
    }

    public String getNowYearlySalary() {
        return nowYearlySalary;
    }

    public void setNowYearlySalary(String nowYearlySalary) {
        this.nowYearlySalary = nowYearlySalary;
    }

    public String getWorkDuty() {
        return workDuty;
    }

    public void setWorkDuty(String workDuty) {
        this.workDuty = workDuty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getStartUnivercityTime() {
        return startUnivercityTime;
    }

    public void setStartUnivercityTime(Date startUnivercityTime) {
        this.startUnivercityTime = startUnivercityTime;
    }

    public Date getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }

    public int getPartTimeJob() {
        return partTimeJob;
    }

    public void setPartTimeJob(int partTimeJob) {
        this.partTimeJob = partTimeJob;
    }
}
