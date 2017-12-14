package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.usermanage.entity.*;
import com.bdssite.modules.usermanage.services.*;
import com.bdssite.tool.CommonTool;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.PagingDto;
import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.boot.internal.SessionFactoryOptionsImpl;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.*;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Role Model Created by Ed_cc on 2017/2/18.
 * Permission Model Created by D on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/AuthorizationManage")
public class AuthorizationManageController implements ServletContextAware {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OperateLogService operateLogService;
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo userInfo(HttpSession session){
        return CommonTool.getUser(session);
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
    public OperationDto permissionUpdate(@ModelAttribute SysPermission permission){
        permissionService.save(permission.getId(),permission);
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
