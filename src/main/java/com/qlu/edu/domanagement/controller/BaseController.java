package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.service.ex.ServiceException;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器的基类
 */
public abstract class BaseController {
    /**
     * 响应状态：成功
     */
    protected static final int OK=2000;

    /**
     * 处理异常相关信息
     * @param e 程序运行时的异常
     * @return  主异常信息
     */
    @ExceptionHandler({ServiceException.class,Exception.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jr=new JsonResult<>(e);
        return jr;
    }
}
