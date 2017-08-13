package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;

/**
 * 单个实体返回Dto
 * Created by D on 2017/7/17.
 */
public class EntityDto<S> extends BaseEntityDto<S> {
    public EntityDto(RequestStatus status,S entity) {
        super(status,entity);
    }
}
