package com.recordme.modules.usermanage.controller;

import com.recordme.modules.usermanage.entity.SysPermission;
import com.recordme.modules.usermanage.entity.SysRole;
import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.OperateLogService;
import com.recordme.modules.usermanage.services.PermissionService;
import com.recordme.modules.usermanage.services.RoleService;
import com.recordme.modules.usermanage.services.UserService;
import com.recordme.modules.usermanage.entity.OperateLog;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
import java.sql.Date;
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
    /**************      权限增删查改      **************/

    @RequestMapping(value = "permissionList", method = RequestMethod.GET)
    public String permissionList(Map<String, Object> map){
        map.put("permissions",permissionService.findAll());
        return "/usermanage/permissionList";
    }

    @RequestMapping(value = "permissionDelete", method = RequestMethod.GET)
    public String deletePermission(Long id){
        permissionService.delete(id);
        return  "redirect:/AuthorizationManage/permissionList";
    }

    @RequestMapping(value = "permissionView", method = RequestMethod.GET)
    public String permissionView(Map<String, Object> map,Long id){

        if(id != null){
            map.put("permission",permissionService.findById(id));
        }else{
            map.put("permission",new SysPermission());
        }

        return "/usermanage/permissionView";
    }


    @RequestMapping(value = "permissionUpdate", method = RequestMethod.POST)
    public String permissionUpdate(Map<String, Object> map,SysPermission permission){
        permissionService.save(permission);
        return "redirect:/AuthorizationManage/permissionView?id=" + permission.getId();
    }


    /**************      角色增删查改      **************/
    @RequestMapping(value = "roleList", method = RequestMethod.GET)
    public String roleList(Map<String, Object> map){
        map.put("roles",roleService.findAll());
        return "/usermanage/roleList";
    }

    @RequestMapping(value = "roleView", method = RequestMethod.GET)
    public String roleView(Model model,Long id){
        if(id != null){
            model.addAttribute("role",roleService.findOne(id));
        }else{
            model.addAttribute("role",new SysRole());
        }

        return "/usermanage/roleView";
    }

    @RequestMapping(value = "roleUpdate", method = RequestMethod.POST)
    public String roleUpdate(Model model,SysRole role,String permission){
        System.out.println(permission);
        if(permission != null){
            List<SysPermission> permissionList = new ArrayList<>();

            for(String permissio : permission.split(",")){
                permissionList.add(permissionService.findById(Long.parseLong(permissio)));
            }
            role.setPermissions(permissionList);
        }
        roleService.save(role);
        return "redirect:/AuthorizationManage/roleView?id=" + role.getId();
    }


    /**************      用户增删查改      **************/
    @RequestMapping(value = "userInfoList", method = RequestMethod.GET)
    public String userInfoList(Map<String, Object> map){
        map.put("userInfos",userService.findAll());
        return "/usermanage/userInfoList";
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


    @RequestMapping(value = "operateLogList", method = RequestMethod.GET)
    public String operateLogList(Map<String, Object> map){
        map.put("operateLogs",operateLogService.findAll());
        return "/usermanage/operateLogList";
    }


    /**************      管理日志用户增删查改      **************/
    @ResponseBody
    @RequestMapping(value = "operateLogData",method = RequestMethod.GET)
    public HashMap<String,Object> operateLogData(String userInfoId){
        HashMap<String,Object> result = new HashMap<>();
        if(userInfoId != null){
            UserInfo userInfo  = userService.findOne(Long.parseLong(userInfoId));
            if(userInfo.getOperateLogs() != null){
                result.put("hadOperateLogs",userInfo.getOperateLogs());
            }
        }
        result.put("allOperateLogs",operateLogService.findAll());
        return result;
    }

    @RequestMapping(value = "operateLogDate",method = RequestMethod.GET)
    public HashMap<String,Object> operateLogDate(Date date){
        HashMap<String,Object> result = new HashMap<>();
        if(date!=null){
            result.put("dateOperateLogs",operateLogService.findByoperateTime(date));
        }
        return result;
    }

    @RequestMapping(value = "operateLogDateScope",method = RequestMethod.GET)
    public HashMap<String,Object> operateLogDateScope(Date start,Date end){
        HashMap<String,Object> result = new HashMap<>();
        ArrayList<OperateLog> list = new ArrayList<>();

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);

        if(startDate!=null&&endDate!=null){
            if (startDate.after(endDate)) {
                Calendar swap = startDate;
                startDate = endDate;
                endDate = swap;
            }
            int days = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);
            int y2 = endDate.get(Calendar.YEAR);
            if (startDate.get(Calendar.YEAR) != y2) {
                startDate = (Calendar) startDate.clone();
                do {
                    days += startDate.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
                    startDate.add(Calendar.YEAR, 1);
                } while (startDate.get(Calendar.YEAR) != y2);
            }


            for(int i=0;i<days;i++){
//                list.add(operateLogService.findByoperateTime(startDate.getTime()))
            }

            result.put("dateScopeOperateLogs",list);
        }
        return result;
    }

//    @ResponseBody
//    @RequestMapping(value = "operateLogDelete",method = RequestMethod.GET)
//    public HashMap<String,Object> operateLogDelete(String operateLogId){
//        HashMap<String,Object> result = new HashMap<>();
//        if(operateLogId!=null){
//            operateLogService.delete(Long.parseLong(operateLogId));
//        }
//        result.put("allOperateLogs",operateLogService.findAll());
//        return result;
//    }



}
