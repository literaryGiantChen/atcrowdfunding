package com.diu.crowd.utils;

/**
 * 用于统一项目中所有 Ajax 请求的返回值类型
 *
 * @author DIU
 * @date 2021/10/31 22:45
 */
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";

    /**
     * 返回操作结果为成功，不带数据
     *
     * @return ResultEntity泛型对象
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<>(SUCCESS, NO_MESSAGE, null);
    }

    /**
     * 返回操作结果为成功，携带数据
     *
     * @param data 要返回页面的数据
     * @return ResultEntity泛型对象
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<>(SUCCESS, NO_MESSAGE, data);
    }

    /**
     * 返回操作结果为失败，不带数据
     *
     * @param message 要返回页面的信息
     * @return ResultEntity泛型对象
     */
    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<>(FAILED, message, null);
    }

    private String operationResult;
    private String operationMessage;
    private T queryData;

    public ResultEntity() {
    }

    public ResultEntity(String operationResult, String operationMessage, T queryData) {
        super();
        this.operationResult = operationResult;
        this.operationMessage = operationMessage;
        this.queryData = queryData;
    }

    @Override
    public String toString() {
        return "AjaxResultEntity [operationResult=" + operationResult + ", operationMessage=" + operationMessage + ", queryData=" + queryData + "]";
    }


    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getOperationMessage() {
        return operationMessage;
    }

    public void setOperationMessage(String operationMessage) {
        this.operationMessage = operationMessage;
    }

    public T getQueryData() {
        return queryData;
    }

    public void setQueryData(T queryData) {
        this.queryData = queryData;
    }
}
