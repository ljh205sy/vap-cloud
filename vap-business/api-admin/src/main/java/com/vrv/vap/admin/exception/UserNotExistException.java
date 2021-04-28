package com.vrv.vap.admin.exception;

import lombok.Data;

/**
 * @author liujinhui
 * date 2021/3/30 21:03
 */
@Data
public class UserNotExistException extends RuntimeException {

    private Integer uid;

    public UserNotExistException(Integer  uid) {
        super("用户不存在,uid:" + uid);
        this.uid = uid;
    }

    public UserNotExistException(){
        super("用户不存在！");
    }
}
