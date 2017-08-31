package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;

import java.util.Collection;
import java.util.List;

public class ListDto<S> extends BaseDto {
    private Collection<S> entityList;
    public ListDto(RequestStatus status, Collection<S> entityList) {
        super(status);
        this.entityList = entityList;
    }

    public Collection<S> getEntityList() {
        return entityList;
    }

    public void setEntityList(Collection<S> entityList) {
        this.entityList = entityList;
    }
}
