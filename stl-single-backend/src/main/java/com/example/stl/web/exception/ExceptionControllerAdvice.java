package com.example.stl.web.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Aaric, created on 2021-09-13T17:08.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 流量异常
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(FlowException.class)
    public String handleFlowException(FlowException e) {
        log.error("handleFlowException", e);
        return "sentinel flow error";
    }

    /**
     * 流量异常
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BlockException.class)
    public String handleBlockException(BlockException e) {
        log.error("handleBlockException", e);
        return "sentinel block error";
    }

    /**
     * 其他异常
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public String handleDefaultException(Exception e) {
        log.error("handleDefaultException", e);
        return "default error";
    }
}
