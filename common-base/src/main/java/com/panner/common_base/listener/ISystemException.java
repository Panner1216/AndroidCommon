package com.panner.common_base.listener;

/**
 * @author panzhijie
 * @version 2019-04-18  15:07
 */
public interface ISystemException {
    String DEFALUT = "内部错误！";
    String DATA_ERROR = "参数错误！";
    String PICTURE_ACQUIRE_ERROR = "无法获取图片！";

    Throwable getCause();
}
