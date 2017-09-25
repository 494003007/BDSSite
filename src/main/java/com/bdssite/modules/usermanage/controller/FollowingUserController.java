package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.common.dto.PagingDto;
import com.bdssite.modules.usermanage.entity.SysPermission;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.UserService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by Ed_cc on 2017/9/20.
 */
@Controller
@RequestMapping(value = "/followingUsers")
public class FollowingUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/followingUsersPage")
    public String FollowingUsersPage(){
        return "/followingUsersPage";
    }
    @RequestMapping(value = "/followingUserAdd/{id}",method = RequestMethod.GET)
    @ResponseBody
    public OperationDto followingUserAdd(@PathVariable long id){
        List<UserInfo> currentUserFollowing = CommonTool.getUser().getFollowingUsers();
        UserInfo userFollowed = userService.findOne(id);
        if(!currentUserFollowing.contains(userFollowed))
        {
            currentUserFollowing.add(userFollowed);
            userService.save(CommonTool.getUser());
        }

        return new OperationDto(RequestStatus.SUCCESS);
    }

    @RequestMapping(value = "/followingUserDelete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public OperationDto followingUserDelete(@PathVariable long id){
        List<UserInfo> currentUserFollowing = CommonTool.getUser().getFollowingUsers();
        UserInfo userFollowed = userService.findOne(id);
        if(currentUserFollowing.contains(userFollowed))
        {
            currentUserFollowing.remove(userFollowed);
            userService.save(CommonTool.getUser());
        }

        return new OperationDto(RequestStatus.SUCCESS);
    }

    @RequestMapping(value = "/followingUsers",method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<UserInfo> followingUsers(Integer limit, Integer offset){

            if(limit == null){
                limit = 10;
            }
            if (offset == null){
                offset = 0;
            }else{
                offset /= limit;
            }
            Page<UserInfo> result = userService.queryAllUserInfoPaging(limit,offset);
            return new PagingDto<>(RequestStatus.SUCCESS,result);

    }
}
