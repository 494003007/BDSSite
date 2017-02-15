package com.recordme.modules.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;


/**
 * Created by D on 2017/2/13.
 */
public abstract class BaseService<DAO extends CrudRepository<T, ID>, T, ID extends Serializable> {
    @Autowired
    protected DAO dao;

    public <S extends T> S save(S var1){
        return dao.save(var1);
    }

    public <S extends T> Iterable<S> save(Iterable<S> var1){
        return dao.save(var1);
    }

    public T findOne(ID var1){
        return dao.findOne(var1);
    }

    public boolean exists(ID var1){
        return dao.exists(var1);
    }

    public Iterable<T> findAll(){
        return dao.findAll();
    }

    public Iterable<T> findAll(Iterable<ID> var1){
        return dao.findAll(var1);
    }

    public long count(){
        return dao.count();
    }

    public void delete(ID var1){
        dao.delete(var1);
    }

    public void delete(T var1){
        dao.delete(var1);
    }

    public void delete(Iterable<? extends T> var1){
        dao.delete(var1);
    }

    public void deleteAll(){
        dao.deleteAll();
    }
}
