package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.usermanage.dao.OperateLogDao;
import com.bdssite.modules.usermanage.entity.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by keben on 2017-05-17.
 */
@Service
public class OperateLogService {
    @Autowired
    OperateLogDao dao;

    public OperateLog findOne(Example<OperateLog> operateLogExample){
        return dao.findOne(operateLogExample);
    }

    public List<OperateLog> findAll(Example<OperateLog> operateLogExample){

        return dao.findAll(operateLogExample);
    }

    public List<OperateLog> findAll(){

        return dao.findAll();
    }


}
