package com.yisu.common.core.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


/**
 * @author xuyisu
 * @description 业务异常
 * @date 2020/12/6
 */
public class TokenCheckException extends RuntimeException {

    public TokenCheckException(String msg){
        super(msg);
    }

}
