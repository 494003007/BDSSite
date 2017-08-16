package com.bdssite.modules.usermanage.controller;

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

import java.util.List;

/**
 * Created by Ed_cc on 2017/8/16.
 */
@Controller
@RequestMapping("/ShortMessage")
public class ShortMessageController {

    @Autowired
    private ShortMessageService shortMessageService;

    //TODO:收信箱控制器
    @RequestMapping(value = {"/receive/{id}"},method = RequestMethod.GET)
    public String receiveShortMessage(Model model,@PathVariable Long id){
        UserInfo userInfo = CommonTool.getUser();
        if(userInfo != null && userInfo.getUid()== id){
            List<ShortMessage> shortMessages = shortMessageService.findByToUser(id);
        }

        return "index";
    }
    //TODO:发信箱控制器
    @RequestMapping(value = {"/send/{id}"},method = RequestMethod.GET)
    public String sendShortMessage(Model model,@PathVariable Long id){
        UserInfo userInfo = CommonTool.getUser();
        if(userInfo != null && userInfo.getUid()== id){
            List<ShortMessage> shortMessages = shortMessageService.findByFromUser(id);
        }

        return "index";
    }
    //TODO:发信控制器页面
    @RequestMapping(value = {"/sendLetter"},method = RequestMethod.GET)
    public String sendLetterPage(){
        return "shortMessage/sendLetterPage";
    }
    //TODO:发信控制器
    @RequestMapping(value = {"/sendLetter"},method = RequestMethod.POST)
    public String sendLetter(Model model,ShortMessage shortMessage){
        shortMessageService.save(shortMessage);
        return "";
    }
}
