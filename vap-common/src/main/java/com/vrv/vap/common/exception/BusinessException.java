package com.vrv.vap.common.exception;

/**
 * 业务异常
 *
 * @author wh1107066
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public BusinessException(String message) {
        super(message);
    }
}
