//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MessageDto extends BaseDto {
    public UserInfo currentUser;
    public UserInfo otherUser;
    public Collection<MessageDto.MessageInfo> messageInfo = new ArrayList();


    public MessageDto(RequestStatus status, List<ShortMessage> list, UserInfo currentUser) {
        super(status);
        this.currentUser = currentUser;
        if(list != null &&list.size()!=0) {
            if(((ShortMessage)list.get(0)).getToUser().getUid() == currentUser.getUid()) {
                this.otherUser = ((ShortMessage)list.get(0)).getFromUser();
            } else {
                this.otherUser = ((ShortMessage)list.get(0)).getToUser();
            }

            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                ShortMessage s = (ShortMessage)var4.next();
                this.getMessageInfo().add(new MessageDto.MessageInfo(s.getContent(), Long.valueOf(s.getFromUser().getUid()),s.getSendTime()));
            }
        }

    }

    public UserInfo getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(UserInfo currentUser) {
        this.currentUser = currentUser;
    }

    public UserInfo getOtherUser() {
        return this.otherUser;
    }

    public void setOtherUser(UserInfo otherUser) {
        this.otherUser = otherUser;
    }

    public Collection<MessageDto.MessageInfo> getMessageInfo() {
        return this.messageInfo;
    }

    public void setMessageInfo(Collection<MessageDto.MessageInfo> messageInfo) {
        this.messageInfo = messageInfo;
    }


    class MessageInfo {
        Long fromUserId;
        String messageContent;
        Timestamp sendTime;
        MessageInfo(String messageContent, Long fromUserId, Timestamp sendTime) {
            this.fromUserId = fromUserId;
            this.messageContent = messageContent;
            this.sendTime = sendTime;
        }

        public Long getFromUserId() {
            return this.fromUserId;
        }

        public void setFromUserId(Long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public String getMessageContent() {
            return this.messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }
        public Timestamp getSendTime() {
            return this.sendTime;
        }

        public void setSendTime(Timestamp sendTime) {
            this.sendTime = sendTime;
        }

    }
}
