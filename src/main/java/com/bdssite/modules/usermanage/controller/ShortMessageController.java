package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.PermissionService;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Controller
@RequestMapping("/shortMessage")
public class ShortMessageController {

    @Autowired
    private ShortMessageService shortMessageService;

    //TODO:收信箱控制器
    @RequestMapping(value = {"/inbox/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public ListDto<ShortMessage> receiveShortMessage(Model model,@PathVariable Long id){
        UserInfo userInfo = CommonTool.getUser();
        if(userInfo != null && userInfo.getUid()== id){
            List<ShortMessage> shortMessages = shortMessageService.findByToUser(userInfo);
            return new ListDto<>(RequestStatus.SUCCESS, shortMessages);
        }
        else
          return new ListDto<>(RequestStatus.PERMISSION_Denied, new ArrayList<ShortMessage>());
    }
    //TODO:发信箱控制器
    @RequestMapping(value = {"/sent/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public ListDto<ShortMessage> sendShortMessage(Model model,@PathVariable Long id){
        UserInfo userInfo = CommonTool.getUser();
        if(userInfo != null && userInfo.getUid()== id){
            List<ShortMessage> shortMessages = shortMessageService.findByFromUser(userInfo);
            return new ListDto<>(RequestStatus.SUCCESS, shortMessages);
        }
    else
        return new ListDto<>(RequestStatus.PERMISSION_Denied, new ArrayList<ShortMessage>());
    }
    //TODO:发信控制器页面
    @RequestMapping(value = {"/sendMessage"},method = RequestMethod.GET)
    public String sendMessagePage(){
        return "shortMessage/sendMessagePage";
    }
    //TODO:发信控制器
    @RequestMapping(value = {"/sendMessage"},method = RequestMethod.POST)
    public String sendMessage(Model model,ShortMessage shortMessage){
        shortMessageService.save(shortMessage);
        return "shortMessage/viewSent";
    }
    //TODO:阅读站内信
    @RequestMapping(value = {"/readMessage/{id}"},method = RequestMethod.GET)
    public EntityDto<ShortMessage> readMessage(Model model,@PathVariable Long id){
        ShortMessage shortMessage = shortMessageService.findOne(id);
        shortMessage.setIsRead(1);
        shortMessageService.save(shortMessage);
        return new EntityDto<>(RequestStatus.SUCCESS,shortMessage);
    }
    //TODO:标记未读
    @RequestMapping(value = {"/markNotReadMessage/{id}"},method = RequestMethod.GET)
    public EntityDto<ShortMessage> markNotReadMessage(Model model,@PathVariable Long id){
        ShortMessage shortMessage = shortMessageService.findOne(id);
        shortMessage.setIsRead(0);
        shortMessageService.save(shortMessage);
        return new EntityDto<>(RequestStatus.SUCCESS,shortMessage);
    }
    //TODO:删除聊天记录
    @RequestMapping(value = {"/deleteMessage"},method = RequestMethod.POST)
    public OperationDto deleteMessage(Model model,@PathVariable Long id,String messageIds){
        String[] stringArray = CommonTool.stringToStringArray(messageIds);
        Collection<Long> idsCollection = new ArrayList<>();
        for (String str:stringArray){
            idsCollection.add(Long.parseLong(str));
        }
        shortMessageService.deleteByIdIn(idsCollection);
        return new OperationDto(RequestStatus.SUCCESS);
    }

}
