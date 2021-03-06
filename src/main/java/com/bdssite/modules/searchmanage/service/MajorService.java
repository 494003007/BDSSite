package com.bdssite.modules.searchmanage.service;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.searchmanage.dao.MajorDao;
import com.bdssite.modules.searchmanage.entity.Major;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MajorService extends BaseService<MajorDao,Major,Long> {
    public Major findByName(String name){
        return dao.findByName(name);
    }
    public boolean existByName(String name){
        return dao.existsByName(name);
    }
    public Collection<Major> findParentMajor(){
        return dao.findMajorsByParentMajorIsNull();
    }
    public Collection<Major> findByParentMajor(Major parentMajor){
        return dao.findMajorsByParentMajor(parentMajor);
    }
}
