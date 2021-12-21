package com.yisu.common.core.result;


import com.yisu.common.core.constant.FwConstants;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 统一返回
 * @author xuyisu
 * @date 2019/9/20
 */
@Data
public class FwResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> FwResult<T> ok() {
        return restResult(null, FwConstants.SUCCESS, FwConstants.MSG_SUCCESS);
    }
    public static <T> FwResult<T> okMsg(String msg) {
        return restResult(null, FwConstants.SUCCESS, msg);
    }
    public static <T> FwResult<T> ok(T data) {
        return restResult(data, FwConstants.SUCCESS, FwConstants.MSG_SUCCESS);
    }

    public static <T> FwResult<T> ok(T data, String msg) {
        return restResult(data, FwConstants.SUCCESS, msg);
    }
    public static <T> FwResult<T> okMeta(T data) {
        return restResult(data, FwConstants.SUCCESS, null);
    }
    public static <T> FwResult<T> ok(T data, String msg, Object meta) {
        return restResult(data, FwConstants.SUCCESS, msg);
    }

    public static <T> FwResult<T> failed() {
        return restResult(null, FwConstants.FAIL, FwConstants.MSG_FAIL);
    }

    public static <T> FwResult<T> failedMsg(String msg) {
        return restResult(null, FwConstants.FAIL, msg);
    }

    public static <T> FwResult<T> tokenFailedMsg(String msg) {
        return restResult(null, FwConstants.RELOGIN, msg);
    }

    public static <T> FwResult<T> failed(T data) {
        return restResult(data, FwConstants.FAIL, FwConstants.MSG_FAIL);
    }

    public static <T> FwResult<T> failed(T data, String msg) {
        return restResult(data, FwConstants.FAIL, msg);
    }

    private static <T> FwResult<T> restResult(T data, int code, String msg) {
        FwResult fwResult = new FwResult();
        fwResult.setCode(code);
        fwResult.setData(data);
        fwResult.setMsg(msg);
        return fwResult;
    }


}
