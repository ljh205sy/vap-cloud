package com.vrv.vap.admin.exception;


import com.vrv.vap.common.page.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author liujinhui
 * date 2021/3/31 23:40
 */
@ControllerAdvice
public class BrowserExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handlerUserNotExistException(UserNotExistException e) {
        logger.error("用户不存在!!! id值为:" + e.getUid(), e);
        return Result.failed("用户不存在,id:" + e.getUid());
    }
}
