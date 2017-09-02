package com.bdssite.modules.searchmanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.searchmanage.entity.Major;
import com.bdssite.modules.searchmanage.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubjectDao extends ExtJpaRepository<Subject,Long> {
    Subject findByName(String name);
    boolean existsByName(String name);
    Collection<Subject> findByMajorsContains(Major maj);
}
