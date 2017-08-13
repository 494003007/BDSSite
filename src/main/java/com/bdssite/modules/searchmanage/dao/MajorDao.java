package com.bdssite.modules.searchmanage.dao;

import com.bdssite.modules.searchmanage.entity.Major;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorDao extends JpaSpecificationExecutor<Major>
        ,PagingAndSortingRepository<Major,Long> {

}
