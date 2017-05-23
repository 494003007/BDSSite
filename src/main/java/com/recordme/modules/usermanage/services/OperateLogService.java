package com.recordme.modules.usermanage.services;

import com.recordme.modules.common.BaseService;
import com.recordme.modules.usermanage.dao.OperateLogDao;
import com.recordme.modules.usermanage.entity.OperateLog;
import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by keben on 2017-05-17.
 */
@Service
public class OperateLogService extends BaseService<OperateLogDao, OperateLog, Long> {


    public OperateLog findById(Long id){
        return dao.findById(id);
    }
    public OperateLog findByoperateUser(UserInfo userInfo){
        return dao.findByoperateUser(userInfo);
    }
    public OperateLog findByoperateTime(Date date){
        return dao.findByoperateTime(date);
    }
//    public OperateLog findByOperateTimeBetween(Date )


}
