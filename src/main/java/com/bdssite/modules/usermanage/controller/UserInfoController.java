package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.common.dto.PagingDto;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.UserService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.Map;

/**
 * Created by Joey_Tsai on 2017/12/14.
 */
@Controller
@RequestMapping(value = "/AuthorizationManage")
public class UserInfoController implements ServletContextAware {
    @Autowired
    private  UserService userService;
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    @Value("${icon_path}")
    private String path;
    /**************      用户增删查改      **************/

    @RequestMapping(value = "personCenter", method = RequestMethod.GET)
    public String personCenter(Map<String, Object> map){
        return "/usermanage/personCenter";
    }

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


    @RequestMapping(value = "userInfos/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<UserInfo> userInfos(@PathVariable Long uid){
        UserInfo result = userService.findByUid(uid);
        return new EntityDto<>(RequestStatus.SUCCESS,result);
    }


    @RequestMapping(value = "userInfoUpdate", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto userInfoUpdate(Map<String, Object> map, @Valid UserInfo userInfo, BindingResult result, HttpSession session){

        System.out.println(result.getAllErrors());

        userService.save(userInfo.getUid(),userInfo);
        userInfo = userService.findByUid(userInfo.getUid());
        CommonTool.updateUser(session,userInfo);
        return new OperationDto(RequestStatus.SUCCESS);

    }

    @RequestMapping(value = "userInfoDelete", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto userInfoDelete(Long id){
        userService.delete(id);
        return new OperationDto(RequestStatus.SUCCESS);
    }
    /**************      用户头像增删查改      **************/
    @RequestMapping(method = RequestMethod.POST, value = "/updateUserIcon",consumes = "multipart/form-data")
    @ResponseBody
    public OperationDto updateUserIcon(HttpServletRequest request, @RequestParam("user_icon") MultipartFile multipartFile, HttpSession session){
        UserInfo userInfo = CommonTool.getUser(session);
        if(multipartFile!=null&&multipartFile.getContentType().equals("image/jpeg")){


            try {
                InputStream i = multipartFile.getInputStream();

                //保存图片到本地
                File file = new File(path+userInfo.getUid()+".jpg");
                if(!file.getParentFile().exists())file.getParentFile().mkdirs();
                FileOutputStream o = new FileOutputStream(file,false);

                int a;
                while ((a = i.read())!=-1){
                    o.write(a);
                }
                i.close();
                o.flush();
                o.close();
                //保存头像路径
                userInfo.setUserIcon(userInfo.getUid()+".jpg");
                userService.save(userInfo);
                CommonTool.updateUser(session,userInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new OperationDto(RequestStatus.SUCCESS);
        }else {
            return new OperationDto(RequestStatus.OPERATION_FALSE);
        }



    }


    @RequestMapping(method = RequestMethod.GET, value = "/getUserIcon")
    @ResponseBody
    public void getUserIcon(HttpServletResponse response, Long id, HttpSession session){
        UserInfo userInfo = CommonTool.getUser(session);
        if(id != null){
            userInfo = userService.findByUid(id);
        }

        OutputStream outputStream = null;
        FileInputStream i = null;
        try {
            outputStream = response.getOutputStream();

            if(userInfo.getUserIcon()==null||userInfo.getUserIcon().equals("")){
                //默认头像路径
                i=new FileInputStream(path+"default_icon.jpg");
            }else {
                i=new FileInputStream(path+userInfo.getUserIcon());
            }
            int a;
            while((a=i.read())!=-1){
                outputStream.write(a);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(outputStream != null){
                    outputStream.close();
                }
                if(i != null){
                    i.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
