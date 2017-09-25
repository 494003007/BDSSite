package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.EntityDto;
import com.bdssite.modules.common.dto.OperationDto;
import com.bdssite.modules.common.dto.PagingDto;
import com.bdssite.modules.usermanage.entity.LexiconCategories;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.LexiconCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by keben on 2017-09-24.
 */

@Controller
@RequestMapping("/lexiconCategories")
public class LaxiconCategoriesControlller {
    @Autowired
    private LexiconCategoriesService lexiconCategoriesService;

    @RequestMapping(value = "lexiconList", method = RequestMethod.GET)
    public String lexiconList(Map<String, Object> map){
        return "/usermanage/lexiconCategories";
    }

    //获取词
    @RequestMapping(value = "lexicons", method = RequestMethod.GET)
    @ResponseBody
    public PagingDto<LexiconCategories> lexicons(Integer limit, Integer offset){
        if(limit == null){
            limit = 10;
        }
        if (offset == null){
            offset = 0;
        }else{
            offset /= limit;
        }
        Page<LexiconCategories> result = lexiconCategoriesService.queryAllLexiconCategoriesPaging(limit,offset);
        return new PagingDto<>(RequestStatus.SUCCESS,result);
    }

    //    删除词
    @RequestMapping(value = "deleteLexicon", method = RequestMethod.GET)
    @ResponseBody
    public OperationDto deleteLexicon(Long id){
        lexiconCategoriesService.delete(id);
        return new OperationDto(RequestStatus.SUCCESS);
    }
    //    设置父节点
    //    查询词
    @RequestMapping(value = "lexicons/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EntityDto<LexiconCategories> lexicons(@PathVariable Long id){
        LexiconCategories result = lexiconCategoriesService.findById(id);
        return new EntityDto<>(RequestStatus.SUCCESS,result);
    }
    //更新词
    @RequestMapping(value = "lexiconsUpdate", method = RequestMethod.POST)
    @ResponseBody
    public OperationDto lexiconsUpdate(Map<String, Object> map, @Valid LexiconCategories lexiconCategories, BindingResult result){

        System.out.println(result.getAllErrors());

        lexiconCategoriesService.save(lexiconCategories.getId(),lexiconCategories);
//        CommonTool.getUser().update(userInfo);
        return new OperationDto(RequestStatus.SUCCESS);

    }
}
