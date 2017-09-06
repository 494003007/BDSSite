package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.common.dto.MessageDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.modules.usermanage.services.UserService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Controller
@RequestMapping("/shortMessage")
public class ShortMessageController {

    @Autowired
    private ShortMessageService shortMessageService;

    @Autowired
    private UserService userService;


    /**
     * 聊天界面获取
     */
    @RequestMapping(value = {"/messagePage"}, method = RequestMethod.GET)
    public String messagePage(Map<String, Object> map) {
        return "shortMessage/messagePage";
    }

    /**
     *  发送信件
     * @param model
     * @param content
     * @param id
     * @return
     */
    @RequestMapping(value = {"/sendMessage/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    public OperationDto sendMessage(Model model,String content, @PathVariable Long id) {
        UserInfo toUser = userService.findOne(id);
        ShortMessage shortMessage1 = new ShortMessage();
        shortMessage1.setFromUser(CommonTool.getUser());
        shortMessage1.setToUser(toUser);
        shortMessage1.setContent(content);
        shortMessageService.save(shortMessage1);
        return new OperationDto(RequestStatus.SUCCESS);
    }

    /**
     * 标记联系人为未读
     *
     * @param model
     * @param id    联系人id
     * @return
     */
    @RequestMapping(value = {"/markNotReadMessage/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<ShortMessage> markNotReadMessage(Model model, @PathVariable Long id) {
        UserInfo currentUser = CommonTool.getUser();
        ShortMessage shortMessage = shortMessageService.findLastNewMessage(currentUser, userService.findOne(id));
        shortMessage.setIsRead(0);
        shortMessageService.save(shortMessage);
        return new EntityDto<>(RequestStatus.SUCCESS, shortMessage);
    }

    /**
     * 阅读信息
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = {"/readMessage/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<MessageDto> readMessage(Model model, @PathVariable Long id) {
        UserInfo currentUser = CommonTool.getUser();
        UserInfo otherUser = userService.findOne(id);

        List<ShortMessage> shortMessageList;

           shortMessageList = shortMessageService.showMessageRecord(currentUser, otherUser);
        //更新信息为已读
        shortMessageService.updateIsReadToTrue(otherUser,currentUser,  0);
        MessageDto messageDto = new MessageDto( shortMessageList, currentUser,otherUser);
        if (messageDto.getOtherUser()==null){
            messageDto.setOtherUser(userService.findOne(id));
        }
        return new EntityDto<>(RequestStatus.SUCCESS, messageDto);
    }

    //TODO:删除聊天记录
    @RequestMapping(value = {"/deleteMessage"}, method = RequestMethod.POST)
    @ResponseBody
    public OperationDto deleteMessage(Model model, @PathVariable Long id, String messageIds) {
        String[] stringArray = CommonTool.stringToStringArray(messageIds);
        Collection<Long> idsCollection = new ArrayList<>();
        for (String str : stringArray) {
            idsCollection.add(Long.parseLong(str));
        }
        shortMessageService.deleteByIdIn(idsCollection);
        return new OperationDto(RequestStatus.SUCCESS);
    }

    /**
     * 显示收到各个用户新信息的最后一条记录
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/showNewMessage"}, method = RequestMethod.GET)
    @ResponseBody
    public ListDto<ShortMessage> showNewMessage(Model model) {
        UserInfo currentUser = CommonTool.getUser();
        List<ShortMessage> shortMessages = shortMessageService.findByToUserAndIsRead(currentUser, 0);
        removeDuplicate(shortMessages);
        return new ListDto<>(RequestStatus.SUCCESS, shortMessages);
    }

    /**
     * 请求所有联系人
     * @param model
     * @return
     */
    @RequestMapping(value = {"/showContact"}, method = RequestMethod.GET)
    @ResponseBody
    public ListDto<ShortMessage> showContact(Model model) {
        UserInfo currentUser = CommonTool.getUser();
        List<ShortMessage> currentUserShortMessage = shortMessageService.findByFromUserOrToUser(currentUser, currentUser);

        for (int i = currentUserShortMessage.size() - 1; i >= 0; i--) {
            for (int j = i + 1; j < currentUserShortMessage.size(); j++) {
                UserInfo fromUser = currentUserShortMessage.get(j).getFromUser();
                UserInfo toUser = currentUserShortMessage.get(j).getToUser();
                if (fromUser.getUid() != currentUser.getUid()) {
                    if (fromUser.equals(currentUserShortMessage.get(i).getFromUser())
                            || fromUser.equals(currentUserShortMessage.get(i).getToUser())) {
                        currentUserShortMessage.remove(i);
                    }
                } else if (toUser.getUid() != currentUser.getUid()) {
                    if (toUser.equals(currentUserShortMessage.get(i).getFromUser())
                            || toUser.equals(currentUserShortMessage.get(i).getToUser())) {
                        currentUserShortMessage.remove(i);
                    }
                }

            }
        }

        Collections.reverse(currentUserShortMessage);
//        List<UserInfo> contact = new ArrayList<>();
//        for (ShortMessage shortMessage:currentUserShortMessage){
//            UserInfo toUser=shortMessage.getToUser();
//            UserInfo fromUser = shortMessage.getFromUser();
//
//            if (toUser.getUid() != currentUser.getUid()){
//                if (!contact.contains(toUser)){
//                    contact.add(toUser);
//                }
//            }
//            else if(fromUser.getUid() != currentUser.getUid()){
//                if (!contact.contains(fromUser)){
//                    contact.add(fromUser);
//                }
//            }
//        }
        return new ListDto<ShortMessage>(RequestStatus.SUCCESS, currentUserShortMessage);
    }

    @RequestMapping(value = {"/updateMessage/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<MessageDto> updateMessage(Model model, @PathVariable Long id) {
        UserInfo currentUser = CommonTool.getUser();
        UserInfo otherUser = userService.findOne(id);
        List<ShortMessage> shortMessageList;

        shortMessageList = shortMessageService.findByFromUserAndToUserAndIsRead(otherUser,currentUser,0);
        //更新信息为已读
        shortMessageService.updateIsReadToTrue(otherUser,currentUser,  0);


        return new EntityDto<>(RequestStatus.SUCCESS, new MessageDto( shortMessageList, currentUser,otherUser));
    }

    /**
     * 删除新信息中显示重复的联系人
     *
     * @param list
     */
    public void removeDuplicate(List<ShortMessage> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getFromUser().equals(list.get(i).getFromUser())) {
                    list.remove(i);
                }
            }
        }

    }
}
