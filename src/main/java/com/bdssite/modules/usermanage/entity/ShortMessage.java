package com.bdssite.modules.usermanage.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 站内信
 * Created by keben on 2017-03-21.
 */
@Entity
public class ShortMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id@GeneratedValue
    private Long id;

    //内容
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String content;
    //发送时间
    @CreationTimestamp
    private Timestamp sendTime;

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    private int isRead;

    @ManyToOne@JoinColumn(name = "fromUser")
    private UserInfo fromUser;

    @ManyToOne@JoinColumn(name = "toUser")
    private UserInfo toUser;

    public UserInfo getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserInfo fromUser) {
        this.fromUser = fromUser;
    }

    public UserInfo getToUser() {
        return toUser;
    }

    public void setToUser(UserInfo toUser) {
        this.toUser = toUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
}
