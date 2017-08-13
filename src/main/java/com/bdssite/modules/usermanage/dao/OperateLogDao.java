package com.bdssite.modules.usermanage.dao;


import com.bdssite.modules.usermanage.entity.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
