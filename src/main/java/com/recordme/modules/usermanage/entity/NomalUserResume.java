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
    public String describe;
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
}
