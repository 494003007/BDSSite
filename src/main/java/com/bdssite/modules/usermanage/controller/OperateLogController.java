package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.common.dto.PagingDto;
import com.bdssite.modules.usermanage.entity.OperateLog;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.OperateLogService;
import com.bdssite.modules.usermanage.services.UserService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.Map;

/**
 * Created by Joey_Tsai on 2017/12/14.
 */
@Controller
@RequestMapping(value = "/AuthorizationManage")
public class OperateLogController {
    /**************      管理日志用户增删查改      **************/

    @Autowired
    private  OperateLogService operateLogService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "operateLogList", method = RequestMethod.GET)
    public String operateLogList(Map<String, Object> map){
        return "/usermanage/operateLogList";
    }

    @RequestMapping(value = "operateLogList/all",method = RequestMethod.GET)
    @ResponseBody
    public ListDto<OperateLog> operateLogList(String userInfoId, Date date){

        OperateLog result = new OperateLog();
        System.out.println("userId: "+userInfoId);
        System.out.println("Date: "+date);
        System.out.println("".equals(userInfoId)+"-------------------------------------------");
        if((!"".equals(userInfoId))&&userInfoId != null){
            UserInfo userInfo  = userService.findOne(Long.parseLong(userInfoId));
            result.setOperateUser(userInfo);
        }
        if(date!=null){
            result.setOperateTime(date);

        }
        Example<OperateLog> example = Example.of(result);
        Iterable<OperateLog> resultDto = operateLogService.findAll(example);
//        map.put("operateLogs", operateLogService.findAll(example));
        return new ListDto<>(RequestStatus.SUCCESS, CommonTool.iterableToList(resultDto));
//        return "/usermanage/operateLogList";
    }



    @RequestMapping(value = "operateLogs", method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<OperateLog> operateLogs(Long userid, Date date, Integer limit, Integer offset){

        if(limit == null){
            limit = 10;
        }
        if (offset == null){
            offset = 0;
        }else{
            offset /= limit;
        }
        OperateLog result = new OperateLog();
        if((!"".equals(userid))&&userid != null){

            result.setUid(userid);
        }

        System.out.println(date);
        if(date!=null){
//            SimpleDateFormat sdf=new SimpleDateFormat();
//            String str=sdf.format(date);
//            if ("".equals(str)) {
//                date = null;
//                System.out.println(date);
//                result.setOperateTime(date);
//            }else
            result.setOperateTime(date);

        }

        Example<OperateLog> example = Example.of(result);
        Page<OperateLog> out = operateLogService.queryAllOperateLogPaging(example,limit,offset);
        return new PagingDto<>(RequestStatus.SUCCESS,out);
    }

    @RequestMapping(value = "operateLogs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<OperateLog> operateLogs(@PathVariable Long id){
        OperateLog result = operateLogService.findOne(id);
        return new EntityDto<>(RequestStatus.SUCCESS,result);
    }

}
