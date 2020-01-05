package com.qlu.edu.domanagement.util;

/**
 * 封装服务器端向客户端响应的结果
 */
public class JsonResult<T> {
    private Integer state;//响应状态码
    private String message;//响应信息
    private T data;//响应数据

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable throwable) {
        this.message=throwable.getMessage();
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
