package com.yisu.common.core.result;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yisu.common.core.constant.FwCommonConstants;
import com.yisu.common.core.model.PageResult;
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

    //分页信息
    private Object meta;

    public static <T> FwResult<T> ok() {
        return restResult(null, FwCommonConstants.SUCCESS, FwCommonConstants.MSG_SUCCESS, null);
    }
    public static <T> FwResult<T> okMsg(String msg) {
        return restResult(null, FwCommonConstants.SUCCESS, msg, null);
    }
    public static <T> FwResult<T> ok(T data) {
        return restResult(data, FwCommonConstants.SUCCESS, FwCommonConstants.MSG_SUCCESS, null);
    }

    public static <T> FwResult<T> ok(T data, String msg) {
        return restResult(data, FwCommonConstants.SUCCESS, msg, null);
    }
    public static <T> FwResult<T> okMeta(T data) {
        return restResult(data, FwCommonConstants.SUCCESS, null, data);
    }
    public static <T> FwResult<T> ok(T data, String msg, Object meta) {
        return restResult(data, FwCommonConstants.SUCCESS, msg, meta);
    }

    public static <T> FwResult<T> failed() {
        return restResult(null, FwCommonConstants.FAIL, FwCommonConstants.MSG_FAIL, null);
    }

    public static <T> FwResult<T> failedMsg(String msg) {
        return restResult(null, FwCommonConstants.FAIL, msg, null);
    }

    public static <T> FwResult<T> failed(T data) {
        return restResult(data, FwCommonConstants.FAIL, FwCommonConstants.MSG_FAIL, null);
    }

    public static <T> FwResult<T> failed(T data, String msg) {
        return restResult(data, FwCommonConstants.FAIL, msg, null);
    }

    private static <T> FwResult<T> restResult(T data, int code, String msg, Object meta) {
        FwResult fwcloudResult = new FwResult();
        fwcloudResult.setCode(code);
        fwcloudResult.setData(data);
        fwcloudResult.setMsg(msg);
        fwcloudResult.setMeta(dealPage(meta));
        return fwcloudResult;
    }


    /**
     * 处理分页信息
     *
     * @param meta
     * @return
     */
    private static Object dealPage(Object meta) {
        if (meta instanceof IPage) {
            IPage pageInfo = (IPage) meta;
            return new PageResult(pageInfo.getCurrent(), pageInfo.getPages(), pageInfo.getTotal());
        }
        return meta;
    }


}
