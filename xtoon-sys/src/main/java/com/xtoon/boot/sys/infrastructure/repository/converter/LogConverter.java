package com.xtoon.boot.sys.infrastructure.repository.converter;

import com.xtoon.boot.sys.domain.model.Log;
import com.xtoon.boot.sys.infrastructure.repository.entity.SysLogDO;

/**
 * 日志转换类
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public class LogConverter {

    public static SysLogDO fromLog(Log log) {
        SysLogDO sysLogDO = new SysLogDO();
        sysLogDO.setUserName(log.getUserName()==null?null:log.getUserName().getName());
        sysLogDO.setIp(log.getIp());
        sysLogDO.setMethod(log.getMethod());
        sysLogDO.setOperation(log.getOperation());
        sysLogDO.setTime(log.getTime());
        return sysLogDO;
    }
}
