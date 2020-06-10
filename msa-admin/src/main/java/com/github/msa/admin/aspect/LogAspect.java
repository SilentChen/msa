package com.github.msa.admin.aspect;

import com.alibaba.fastjson.JSONObject;
import com.github.msa.admin.model.LogModel;
import com.github.msa.admin.service.LogService;
import com.github.msa.admin.util.HttpUtils;
import com.github.msa.admin.util.IPUtils;
import com.github.msa.admin.util.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class LogAspect {
    @Autowired
    private LogService logService;

    @Pointcut("execution(* com.github.msa.*.service.*.*(..)))")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }


    private void saveLog(ProceedingJoinPoint pjp, long time) {
        String userName = SecurityUtils.getUsername();
        if(pjp.getTarget() instanceof LogService) {
            return;
        }

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        LogModel logModel = new LogModel();

        String className = pjp.getTarget().getClass().getName();
        String methdName = signature.getName();
        logModel.setMethod(className + "." + methdName + "()");

        Object[] args = pjp.getArgs();
        try {
            String param = JSONObject.toJSONString(args[0]);
            if(param.length() > 200) {
                param = param.substring(0, 200) + "...";
            }
            logModel.setParams(param);
        }catch (Exception e) {

        }
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        logModel.setIp(IPUtils.getIpAddr(request));
        logModel.setUserName(userName);
        logModel.setTime(time);

        logService.save(logModel);
    }
}
