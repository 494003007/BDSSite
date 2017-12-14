package com.bdssite.config;

import com.bdssite.modules.usermanage.entity.OperateLog;
import com.bdssite.modules.usermanage.services.OperateLogService;
import com.bdssite.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

@ControllerAdvice
public class GlobalExceptionHandlerConfig {

    @Autowired
    private  OperateLogService operateLogService;
    //http://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/updateUserIcon";

    }
    @ExceptionHandler(value = Exception.class)
    //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public void   exceptionHandler(Exception e) {

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateUser(CommonTool.getCurrentUser());
        operateLog.setUid(CommonTool.getCurrentUser().getUid());
        operateLog.setException(String.valueOf(e.toString()));
        operateLog.setOperateTime(new Date(System.currentTimeMillis()));
        operateLogService.save(operateLog);

    }
}