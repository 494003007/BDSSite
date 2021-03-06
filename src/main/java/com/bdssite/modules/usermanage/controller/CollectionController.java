package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.usermanage.entity.CollectionProfession;
import com.bdssite.modules.usermanage.services.CollectionProfessionService;
import com.bdssite.modules.usermanage.services.UserService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

/**
 * Created by Ed_cc on 2017/9/18.
 */
@Controller
@RequestMapping(value = "/collection")
public class CollectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionProfessionService collectionProfessionService;

    @RequestMapping(value = "/collectionPage")
    public String collectionPage(){
        return "collectionPage";
    }
    @RequestMapping(value = "/addCollection",method = RequestMethod.POST)
    public OperationDto addCollection(CollectionProfession collectionProfession, HttpSession httpSession){
        if (!CommonTool.getUser(httpSession).getCollectionProfessions().contains(collectionProfession)){
            CommonTool.getUser(httpSession).getCollectionProfessions().add(collectionProfession);
            userService.save(CommonTool.getUser(httpSession));
        }
        return new OperationDto(RequestStatus.SUCCESS);
    }

}
