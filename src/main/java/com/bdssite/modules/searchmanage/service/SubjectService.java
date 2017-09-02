package com.bdssite.modules.searchmanage.service;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.searchmanage.dao.SubjectDao;
import com.bdssite.modules.searchmanage.entity.Major;
import com.bdssite.modules.searchmanage.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubjectService extends BaseService<SubjectDao,Subject,Long> {
    public Subject findByName(String name){
        return dao.findByName(name);
    }
    public boolean existByName(String name){
        return dao.existsByName(name);
    }
    public Collection<Subject> findByMajor(Major major){
        return dao.findByMajorsContains(major);
    }
}
