package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;

import java.util.List;

public class ListDto<S> extends BaseDto {
    private List<S> entityList;
    public ListDto(RequestStatus status, List<S> entityList) {
        super(status);
        this.entityList = entityList;
    }

    public List<S> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<S> entityList) {
        this.entityList = entityList;
    }
}
