package com.recordme.modules.usermanage.entity;

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

    private String operateContent;
    private Date operateTime;

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
