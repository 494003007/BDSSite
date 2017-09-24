package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.LexiconCategoriesDao;
import com.bdssite.modules.usermanage.entity.LexiconCategories;
import com.bdssite.modules.usermanage.entity.UserInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by Ed_cc on 2017/5/6.
 */
@Component
public class LexiconCategoriesService extends BaseService<LexiconCategoriesDao, LexiconCategories, Long> {

    public Page<LexiconCategories> queryAllLexiconCategoriesPaging(int limit, int offset){
        return dao.findAll(new PageRequest(offset,limit));
    }
    public LexiconCategories findById(Long id){
        return dao.findOne(id);
    }

    public LexiconCategories findOne(Example<LexiconCategories> lexiconCategoriesExample){
        return dao.findOne(lexiconCategoriesExample);
    }


}
