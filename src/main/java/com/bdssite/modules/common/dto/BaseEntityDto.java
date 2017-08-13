package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;

/**
 * Created by D on 2017/7/17.
 */
public abstract class BaseEntityDto<S>  extends BaseDto {
    public S entity;
    public BaseEntityDto(RequestStatus status,S entity) {
        super(status);
        this.entity = entity;
    }

    public S getEntity() {
        return entity;
    }

    public void setEntity(S entity) {
        this.entity = entity;
    }
}
