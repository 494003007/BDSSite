package com.bdssite.modules.common;

/**
 * Created by D on 2017/7/14.
 */
public enum RequestStatus {

    SUCCESS(1000, "请求成功"),
    OPERATION_FALSE(1150,"操作异常"),
    PERMISSION_Denied(1050,"权限不足"),
    SERVER_ERROR(1100, "服务器异常");




    private int code;

    private String msg;

    RequestStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
