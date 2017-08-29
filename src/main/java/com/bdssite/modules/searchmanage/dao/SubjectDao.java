package com.bdssite.modules.searchmanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.searchmanage.entity.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends ExtJpaRepository<Subject,Long> {

}
