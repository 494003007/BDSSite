package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.tool.CommonTool;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.PagingDto;
import com.bdssite.modules.usermanage.entity.SysPermission;
import com.bdssite.modules.usermanage.entity.SysRole;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.OperateLogService;
import com.bdssite.modules.usermanage.services.PermissionService;
import com.bdssite.modules.usermanage.services.RoleService;
import com.bdssite.modules.usermanage.services.UserService;
import com.bdssite.modules.usermanage.entity.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Role Model Created by Ed_cc on 2017/2/18.
 * Permission Model Created by D on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/AuthorizationManage")
public class AuthorizationManageController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OperateLogService operateLogService;

    @RequestMapping(value = "/currentUser", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo userInfo(){
        return CommonTool.getUser();
    }

    /**************      权限增删查改      **************/

    @RequestMapping(value = "permissionList", method = RequestMethod.GET)
    public String permissionList(Map<String, Object> map){
        return "/usermanage/permissionList";
    }

    @RequestMapping(value = "permissions/all", method = RequestMethod.GET)
    @ResponseBody
    public ListDto<SysPermission> permissions(){
        Iterable<SysPermission> result = permissionService.findAll();
        return new ListDto<>(RequestStatus.SUCCESS, CommonTool.iterableToList(result));
    }

    @RequestMapping(value = "permissions", method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<SysPermission> permissions(Integer limit, Integer offset){
        if(limit == null){
            limit = 10;
        }
        if (offset == null){
            offset = 0;
        }else{
            offset /= limit;
        }
        Page<SysPermission> result = permissionService.queryAllPermissionPaging(limit,offset);
        return new PagingDto<>(RequestStatus.SUCCESS,result);
    }

    @RequestMapping(value = "permissions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<SysPermission> permissions(@PathVariable Long id){
        SysPermission result = permissionService.findOne(id);
        return new EntityDto<>(RequestStatus.SUCCESS,result);
    }

    //TODO:未完成功能 暂且返回所有权限
    @RequestMapping(value = "permissions/byRole/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<SysPermission> permissionsByRole(@PathVariable Long id){
        SysPermission result = permissionService.findOne(id);
        return new EntityDto<>(RequestStatus.SUCCESS,result);
    }

    //TODO:未完成功能 暂且返回所有权限
    @RequestMapping(value = "permissions/byUid/{uid}", method = RequestMethod.POST)
    @ResponseBody
    public ListDto<SysPermission> permissionsByUid(@PathVariable Long uid){
        Iterable<SysPermission> result = permissionService.findAll();
        return new ListDto<>(RequestStatus.SUCCESS, CommonTool.iterableToList(result));

    }

    @RequestMapping(value = "permissionDelete", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto deletePermission(Long id){
        permissionService.delete(id);
        return new OperationDto(RequestStatus.SUCCESS);
    }



    @RequestMapping(value = "permissionUpdate", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto permissionUpdate(Map<String, Object> map,SysPermission permission){
        permissionService.save(permission);
        return new OperationDto(RequestStatus.SUCCESS);
    }


    /**************      角色增删查改      **************/
    @RequestMapping(value = "roleList", method = RequestMethod.GET)
    public String roleList(Map<String, Object> map){
        return "/usermanage/roleList";
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<SysRole> roles(Integer limit, Integer offset){
        if(limit == null){
            limit = 10;
        }
        if (offset == null){
            offset = 0;
        }else{
            offset /= limit;
        }
        Page<SysRole> result = roleService.queryAllRolePaging(limit,offset);
        return new PagingDto<>(RequestStatus.SUCCESS,result);
    }

    @RequestMapping(value = "roles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<SysRole> roles(@PathVariable Long id){
        SysRole result = roleService.findOne(id);
        return new EntityDto<>(RequestStatus.SUCCESS,result);
    }

    @RequestMapping(value = "roleDelete", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto deleteRole(Long id){
        roleService.delete(id);
        return new OperationDto(RequestStatus.SUCCESS);
    }

    @RequestMapping(value = "roleUpdate", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto roleUpdate(Model model,SysRole role,String permission){
        System.out.println(permission);
        if(permission != null){
            List<SysPermission> permissionList = new ArrayList<>();

            for(String permissio : permission.split(",")){
                permissionList.add(permissionService.findById(Long.parseLong(permissio)));
            }
            role.setPermissions(permissionList);
        }
        roleService.save(role);
        return new OperationDto(RequestStatus.SUCCESS);
    }


    /**************      用户增删查改      **************/


    @RequestMapping(value = "userInfoList", method = RequestMethod.GET)
    public String userInfoList(Map<String, Object> map){
        return "/usermanage/userInfoList";
    }

    @RequestMapping(value = "userInfos", method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<UserInfo> userInfos(Integer limit, Integer offset){
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


    @RequestMapping(value = "userInfoView", method = RequestMethod.GET)
    public String userInfoView(Model model,Long uid){
        if(uid != null){
            model.addAttribute("userInfo",userService.findOne(uid));
        }else{
            model.addAttribute("userInfo",new UserInfo());
        }

        return "/usermanage/userInfoView";
    }

    @RequestMapping(value = "userInfoUpdate", method = RequestMethod.POST)
    public String userInfoUpdate(Model model,UserInfo userInfo,String roles){
        System.out.println(roles);
        if(roles != null){
            List<SysRole> roleList = new ArrayList<>();

            for(String role : roles.split(",")){
                roleList.add(roleService.findById(Long.parseLong(role)));
            }
            userInfo.setRoles(roleList);
        }
        userService.save(userInfo);
        return "redirect:/AuthorizationManage/userInfoView?id=" + userInfo.getUid();
    }
    /**************      权限角色增删查改      **************/

    @ResponseBody
    @RequestMapping(value = "permissionData", method = RequestMethod.POST)
    public HashMap<String,Object> permissionData(String roleId){
        HashMap<String,Object> result = new HashMap<>();
        if(roleId != null){
            SysRole sysRole  = roleService.findOne(Long.parseLong(roleId));
            if(sysRole.getPermissions() != null){

                result.put("hadPermission",sysRole.getPermissions());
            }
        }
        result.put("allPermission",permissionService.findAll());
        return result;
    }

    @RequestMapping(value = "permissionTree")
    public String permissionTree(Model model,String roleId){
        if(roleId != null){
            model.addAttribute("roleId",roleId);
        }
        return "usermanage/permissionTree";
    }

    /**************      角色用户增删查改      **************/

    @ResponseBody
    @RequestMapping(value = "roleData", method = RequestMethod.POST)
    public HashMap<String,Object> roleData(String userInfoId){
        HashMap<String,Object> result = new HashMap<>();
        if(userInfoId != null){
            UserInfo userInfo  = userService.findOne(Long.parseLong(userInfoId));
            if(userInfo.getRoles() != null){
                result.put("hadRoles",userInfo.getRoles());
            }
        }
        result.put("allRoles",roleService.findAll());
        return result;
    }

    @RequestMapping(value = "roleTree")
    public String roleTree(Model model,String userInfoId){
        if(userInfoId != null){
            model.addAttribute("userInfoId",userInfoId);
        }
        return "usermanage/roleTree";
    }

    /**************      管理日志增删查改      **************/


//    @RequestMapping(value = "operateLogList", method = RequestMethod.GET)
//    public String operateLogList(Map<String, Object> map){
//        map.put("operateLogs",operateLogService.findAll());
//        return "/usermanage/operateLogList";
//    }


    /**************      管理日志用户增删查改      **************/

    @RequestMapping(value = "operateLogList", method = RequestMethod.GET)
    public String operateLogList(Map<String, Object> map){
        return "/usermanage/operateLogList";
    }

    @RequestMapping(value = "operateLogList/all",method = RequestMethod.GET)
    @ResponseBody
    public ListDto<OperateLog> operateLogList(String userInfoId,Date date){

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

//    @RequestMapping(value = "operateLogs", method = RequestMethod.GET)
//    @ResponseBody
//    public PagingDto<OperateLog> operateLogs(Integer limit, Integer offset){
//        if(limit == null){
//            limit = 10;
//        }
//        if (offset == null){
//            offset = 0;
//        }else{
//            offset /= limit;
//        }
//        Page<OperateLog> result = operateLogService.queryAllOperateLogPaging(limit,offset);
//        return new PagingDto<>(RequestStatus.SUCCESS,result);
//    }


    @RequestMapping(value = "operateLogs", method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<OperateLog> operateLogs( Long userid, Date date,Integer limit, Integer offset){

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






    /**************      管理日志日期增删查改      **************/
//    @RequestMapping(value = "operateLogList",method = RequestMethod.GET)
//    public HashMap<String,Object> operateLogDate(Date date){
//        HashMap<String,Object> result = new HashMap<>();
//        if(date!=null){
//            result.put("operateLogs",operateLogService.findByoperateTime(date));
//        }
//        return result;
//    }

//    @RequestMapping(value = "operateLogDateScope",method = RequestMethod.GET)
//    public HashMap<String,Object> operateLogDateScope(Date start,Date end){
//        HashMap<String,Object> result = new HashMap<>();
//        if(start!=null&&end!=null){
//            result.put("operateLogs",operateLogService.findByOperateTimeBetween(start,end));
//        }
//        return result;
//    }






}
