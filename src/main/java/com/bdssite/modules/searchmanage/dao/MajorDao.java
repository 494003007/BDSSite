package com.bdssite.modules.searchmanage.dao;

import com.bdssite.modules.common.repository.ExtJpaRepository;
import com.bdssite.modules.searchmanage.entity.Major;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MajorDao extends ExtJpaRepository<Major,Long> {
    Major findByName(String name);
    boolean existsByName(String name);
    Collection<Major> findMajorsByParentMajorIsNull();
    Collection<Major> findMajorsByParentMajor(Major parentMajor);

}
