package com.yisu.common.core.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


/**
 * @author xuyisu
 * @description 业务异常
 * @date 2020/12/6
 */
public class BusinessException extends RuntimeException {
    private Integer status = INTERNAL_SERVER_ERROR.value();

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
