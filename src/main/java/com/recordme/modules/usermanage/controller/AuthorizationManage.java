package com.recordme.modules.usermanage.controller;

import com.recordme.modules.usermanage.entity.SysPermission;
import com.recordme.modules.usermanage.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by D on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/AuthorizationManage")
public class AuthorizationManage {
    @Autowired
    private PermissionService permissionService;

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
        System.out.println("========================================================" + permission);
        permissionService.save(permission);
        return "redirect:/AuthorizationManage/permissionView?id=" + permission.getId();
    }



//    @RequestMapping(value = "permission/list", method = RequestMethod.GET)
//    public String permissionList(){
//        return "/usermanage/permissionList";
//    }

}
