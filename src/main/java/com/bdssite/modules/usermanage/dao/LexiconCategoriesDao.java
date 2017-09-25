package com.bdssite.modules.usermanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.usermanage.entity.LexiconCategories;
import org.springframework.stereotype.Repository;

/**
 * Created by Ed_cc on 2017/5/6.
 */
@Repository
public interface LexiconCategoriesDao extends ExtJpaRepository<LexiconCategories, Long> {
//    public LexiconCategories findByClassification(String classification);
//    public LexiconCategories findByDescription(String description);
}
