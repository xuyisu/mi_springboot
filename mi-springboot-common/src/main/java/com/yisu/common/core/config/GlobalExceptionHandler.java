package com.yisu.common.core.config;

import com.yisu.common.core.exception.BusinessException;
import com.yisu.common.core.exception.TokenCheckException;
import com.yisu.common.core.result.FwResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.security.InvalidParameterException;

/**
 * @description 异常处理
 * @author xuyisu
 * @date 2020-12-06
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidParameterException.class, IllegalArgumentException.class, MissingServletRequestParameterException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public FwResult<Void> paramException(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            return FwResult.failedMsg(((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }

        if (e.getMessage() != null) {
            return FwResult.failedMsg(e.getMessage());
        }

        return FwResult.failed();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BusinessException.class})
    public FwResult businessException(BusinessException e) {
        log.error("BusinessException 异常",e);
        return FwResult.failedMsg("系统内部错误,请稍后重试");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({TokenCheckException.class})
    public FwResult businessException(TokenCheckException e) {
        log.error("BusinessException 异常",e);
        return FwResult.tokenFailedMsg(e.getMessage());
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex .
     * @return .
     */
    @ExceptionHandler(value = Exception.class)
    public FwResult errorHandler(Exception ex, HttpServletRequest request)
    {
        if (null != request) {
            StringBuilder strBuild = new StringBuilder();
            strBuild.append("global catch reqUrl:");
            strBuild.append(request.getRequestURI());
            if (HttpMethod.GET.matches(request.getMethod())) {
                strBuild.append(" queryString:");
                strBuild.append(request.getQueryString());
            }
            log.error(strBuild.toString(), ex);
            return FwResult.failedMsg("系统内部错误,请稍后重试");
        }
        else
        {
            log.error("global catch", ex);
            return FwResult.failedMsg("系统内部错误,请稍后重试");
        }
    }




    /**
     * 前端请求方式异常捕捉处理
     *
     * @param ex .
     * @return .
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public FwResult httpMessageParseErrorHandler(HttpMessageNotReadableException ex) {
        log.info("HttpMessageNotReadable catch", ex);
        return FwResult.failedMsg("系统内部错误,请稍后重试");
    }





}
