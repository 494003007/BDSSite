package com.recordme.modules.usermanage.dao;

import com.recordme.modules.usermanage.entity.LexiconCategories;
import com.recordme.modules.usermanage.entity.SysPermission;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ed_cc on 2017/5/6.
 */
public interface LexiconCategoriesDao extends CrudRepository<LexiconCategories, Long> {
}
