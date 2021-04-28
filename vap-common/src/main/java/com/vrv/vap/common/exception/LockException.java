package com.vrv.vap.common.exception;

/**
 * 分布式锁异常
 *
 * @author wh1107066
 */
public class LockException extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public LockException(String message) {
        super(message);
    }
}
