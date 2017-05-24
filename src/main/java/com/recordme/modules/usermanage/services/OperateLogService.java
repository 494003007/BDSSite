package com.recordme.modules.usermanage.services;

import com.recordme.modules.common.BaseService;
import com.recordme.modules.usermanage.dao.OperateLogDao;
import com.recordme.modules.usermanage.entity.OperateLog;
import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
