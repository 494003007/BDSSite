package com.bdssite.modules.searchmanage.dao;

import com.bdssite.modules.searchmanage.entity.Subject;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends JpaSpecificationExecutor<Subject>
        ,PagingAndSortingRepository<Subject,Long> {

}
