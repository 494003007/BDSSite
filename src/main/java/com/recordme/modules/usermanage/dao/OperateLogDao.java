package com.recordme.modules.usermanage.dao;


import com.recordme.modules.usermanage.entity.OperateLog;
import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by keben on 2017-05-17.
 */
@Repository
public interface OperateLogDao extends CrudRepository<OperateLog, Long> {
    OperateLog findById(Long id);
    OperateLog findByoperateUser(UserInfo userInfo);
}
