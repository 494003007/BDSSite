package com.bdssite.modules.common.repository;

import com.bdssite.modules.common.BeanUtil;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class SimpleExtJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtJpaRepository<T, ID> {

    private final EntityManager em;

    public SimpleExtJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }

    public SimpleExtJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    @Transactional
    public T dynamicSave(ID id, T entity) {
        T managedEntity = this.findOne(id);
        T mergedEntity;
        if (managedEntity == null) {
            em.persist(entity);
            mergedEntity = entity;
        } else {
            BeanUtil.copyNonNullProperties(entity, managedEntity);
            em.merge(managedEntity);
            mergedEntity = managedEntity;
        }
        return mergedEntity;
    }

}