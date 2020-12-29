package com.panner.common_base.exception;


import com.panner.common_base.listener.SystemExceptionNoVessel;

/**
 * 自定异常，带内容，不被过滤
 *
 * @author Panner
 * @version 2016-10-12 17:09
 */
@SystemExceptionNoVessel
public class SystemContentException extends SystemException {

    public SystemContentException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public SystemContentException(String errorMessage) {
        this(errorMessage, null);
    }

    public SystemContentException(Throwable e) {
        this(e.getMessage(), e);
    }
}
