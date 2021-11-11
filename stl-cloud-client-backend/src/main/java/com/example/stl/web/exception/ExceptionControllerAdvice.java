package com.example.stl.web.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常控制器建议
 *
 * @author Aaric, created on 2021-11-11T10:54.
 * @version 0.5.0-SNAPSHOT
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {


    /**
     * Sentinel Exception - 501
     *
     * @param cause 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BlockException.class)
    public String handleBlockException(BlockException cause) {
        String route = cause.getRule().getResource();
        Integer errCode = 501;
        String errMsg = "";

        if (cause instanceof FlowException) {
            log.error("fallback: sentinel flow exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel flow exception");

        } else if (cause instanceof ParamFlowException) {
            log.error("fallback: sentinel param flow exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel param flow exception");

        } else if (cause instanceof DegradeException) {
            log.error("fallback: sentinel degrade exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel degrade exception");

        } else if (cause instanceof AuthorityException) {
            log.error("fallback: sentinel authority exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel authority exception");

        } else if (cause instanceof SystemBlockException) {
            log.error("fallback: sentinel system block exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel system block exception");

        } else if (cause instanceof BlockException) {
            log.error("fallback: sentinel block exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel block exception");

        } else {
            log.error("fallback: default sentinel error, route={}", route);
            errMsg = String.format("%d: %s", 501, "default sentinel error");

        }

        return errMsg;
    }

    /**
     * Default Exception - 500
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public String handleDefaultException(Exception e) {
        log.error("handleDefaultException", e);
        return "500: default exception";
    }
}
