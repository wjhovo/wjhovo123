package cn.jxust.secondhandmarket.controller;

import cn.jxust.secondhandmarket.exception.InvalidArgsException;
import cn.jxust.secondhandmarket.exception.NullDataException;
import cn.jxust.secondhandmarket.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 通用异常处理器
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Message exceptionHandler(Exception e) {
        logger.error(e.toString());
        e.printStackTrace();
        return Message.SERVER_ERROR;
    }

    /**
     * 参数缺失异常处理器
     */
    @ResponseBody
    @ExceptionHandler(InvalidArgsException.class)
    public Message uploadException() {
        return Message.INVALID_ARGS;
    }

    /**
     * 空数据异常处理器
     */
    @ResponseBody
    @ExceptionHandler(NullDataException.class)
    public Message nullDataException() {
        return Message.NULL_DATA;
    }

    /**
     * 请求方式错误异常处理器
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Message requestMethodNotSupport() {
        return Message.METHOD_NOT_SUPPORT;
    }
}
