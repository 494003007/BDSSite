package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ed_cc on 2017/8/31.
 */
public class MessageDto extends BaseDto {
    public MessageDto(RequestStatus status, List<ShortMessage> list, UserInfo currentUser) {
        super(status);
        messageInfo = new ArrayList<>();
        toUser = currentUser;
        if (list != null) {
            if (list.get(0).getToUser()==currentUser) {
                fromUser = list.get(0).getFromUser();
            } else
                fromUser = list.get(0).getToUser();

            for (ShortMessage s : list) {
                getMessageInfo().add(new MessageInfo(s.getContent(), s.getFromUser().getUid()));
            }

        }

    }

    public UserInfo getToUser() {
        return toUser;
    }

    public void setToUser(UserInfo toUser) {
        this.toUser = toUser;
    }

    public UserInfo getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserInfo fromUser) {
        this.fromUser = fromUser;
    }

    public List<MessageInfo> getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(List<MessageInfo> messageInfo) {
        this.messageInfo = messageInfo;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public UserInfo toUser;
    public UserInfo fromUser;
    public List<MessageInfo> messageInfo;
    public Date sendTime;

    class MessageInfo {
        //发送者flag
        MessageInfo(String messageContent, Long fromUserId) {
            this.fromUserId = fromUserId;
            this.messageContent = messageContent;
        }

        Long fromUserId;
        String messageContent;
    }
}
