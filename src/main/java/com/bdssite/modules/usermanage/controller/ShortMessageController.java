package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.common.dto.MessageDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.PermissionService;
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

    @Autowired
    private UserService userService;



    //TODO:发信控制器
    @RequestMapping(value = {"/sendMessage"},method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(Model model,ShortMessage shortMessage){
        shortMessageService.save(shortMessage);
        return "shortMessage/viewSent";
    }

    //TODO:标记未读
    @RequestMapping(value = {"/markNotReadMessage/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<ShortMessage> markNotReadMessage(Model model,@PathVariable Long id){
        ShortMessage shortMessage = shortMessageService.findOne(id);
        shortMessage.setIsRead(0);
        shortMessageService.save(shortMessage);
        return new EntityDto<>(RequestStatus.SUCCESS,shortMessage);
    }

    /**
     * 阅读信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = {"/readMessage/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public MessageDto readMessage(Model model,@PathVariable Long id){
         UserInfo currentUser = CommonTool.getUser();
            UserInfo user = userService.findOne(id);
            //更新信息为已读
            shortMessageService.updateIsReadToTrue(currentUser,user,0);
            return new MessageDto(RequestStatus.SUCCESS, shortMessageService.showMessageRecord(currentUser,user),currentUser);
    }

    //TODO:删除聊天记录
    @RequestMapping(value = {"/deleteMessage"},method = RequestMethod.POST)
    @ResponseBody
    public OperationDto deleteMessage(Model model,@PathVariable Long id,String messageIds){
        String[] stringArray = CommonTool.stringToStringArray(messageIds);
        Collection<Long> idsCollection = new ArrayList<>();
        for (String str:stringArray){
            idsCollection.add(Long.parseLong(str));
        }
        shortMessageService.deleteByIdIn(idsCollection);
        return new OperationDto(RequestStatus.SUCCESS);
    }
    //TODO:新信息.
    @RequestMapping(value = {"/showNewMessage}"},method = RequestMethod.GET)
    @ResponseBody
    public ListDto<ShortMessage> showNewMessage(Model model){
        UserInfo currentUser = CommonTool.getUser();
            List<ShortMessage> shortMessages = shortMessageService.findByToUserAndIsRead(currentUser,0);
            return new ListDto<>(RequestStatus.SUCCESS, shortMessages);
    }

    /**
     * 删除新信息中显示重复的联系人
     * @param list
     */
    public  void  removeDuplicate(List<ShortMessage> list)  {
        for  ( int  i  =   list.size()  -   1 ; i  >=  0; i -- )  {
            for  ( int  j  =  i+1 ; j  <  list.size()  ; j ++ )  {
                if  (list.get(j).getFromUser().equals(list.get(i).getFromUser()))  {
                    list.remove(j);
                }
            }
        }

    }
}
