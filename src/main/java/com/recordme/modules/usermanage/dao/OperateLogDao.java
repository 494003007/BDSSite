package com.recordme.modules.usermanage.dao;


import com.recordme.modules.usermanage.entity.OperateLog;
import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by keben on 2017-05-17.
 */
@Repository
public interface OperateLogDao extends JpaRepository<OperateLog,Long> {
//    OperateLog findById(Long id);
//    OperateLog findByoperateUser(UserInfo userInfo);
//    OperateLog findByoperateTime(Date date);
//    List<OperateLog> findByoperateTimeBetween(Date start,Date end);
//    List<OperateLog> findByExample(Example<OperateLog> example);
}
