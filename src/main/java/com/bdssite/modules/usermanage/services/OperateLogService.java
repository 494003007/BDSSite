package com.bdssite.modules.usermanage.services;

import com.bdssite.modules.common.BaseService;
import com.bdssite.modules.usermanage.dao.OperateLogDao;
import com.bdssite.modules.usermanage.entity.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by keben on 2017-05-17.
 */
@Service
public class OperateLogService extends BaseService<OperateLogDao,OperateLog,Long> {



    public OperateLog findOne(Example<OperateLog> operateLogExample){
        return dao.findOne(operateLogExample);
    }

    public List<OperateLog> findAll(Example<OperateLog> operateLogExample){

        return dao.findAll(operateLogExample);
    }

    public List<OperateLog> findAll(){

        return dao.findAll();
    }

    public Page<OperateLog> queryAllOperateLogPaging(int limit, int offset){
        return dao.findAll(new PageRequest(offset,limit));
    }

//    public Page<OperateLog> queryAllOperateLogPaging(Example<OperateLog> operateLogExample,int limit, int offset){
//        return dao.findAll(operateLogExample,new PageRequest(offset,limit));
//    }


}
