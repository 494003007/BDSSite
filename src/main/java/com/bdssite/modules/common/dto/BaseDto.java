package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;

/**
 * Created by D on 2017/7/17.
 */
public abstract class BaseDto {
    int statusCode;

    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseDto(RequestStatus status){
        this.setStatusCode(status.getCode());
        this.setMessage(status.getMsg());
    }
}
