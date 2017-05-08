package com.recordme.modules.usermanage.dao;

import com.recordme.modules.usermanage.entity.LexiconCategories;
import com.recordme.modules.usermanage.entity.SysPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ed_cc on 2017/5/6.
 */
@Repository
public interface LexiconCategoriesDao extends CrudRepository<LexiconCategories, Long> {
}
