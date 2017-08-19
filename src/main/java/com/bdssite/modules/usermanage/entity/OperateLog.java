package com.bdssite.modules.usermanage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * 操作日志表
 * Created by keben on 2017-03-25.
 */
@Entity
public class OperateLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne@JoinColumn(name = "operateUser")
    private UserInfo operateUser;

    public UserInfo getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(UserInfo operateUser) {
        this.operateUser = operateUser;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    private Long uid;
    private String operateContent;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date operateTime;
    @Column(columnDefinition="varchar(128) default 'NoExcepetion'")
    private String exception;

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
