package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;

public class OperationDto extends BaseDto {
    public OperationDto(RequestStatus status) {
        super(status);
    }
}
