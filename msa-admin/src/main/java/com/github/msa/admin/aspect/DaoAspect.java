package com.github.msa.admin.aspect;

import com.github.msa.admin.util.SecurityUtils;
import com.github.msa.common.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
@Configuration
public class DaoAspect {
    private static final String createBy        = "createBy";
    private static final String createTime      = "createTime";
    private static final String lastUpdateBy    = "lastUpdateBy";
    private static final String lastUpdateTime  = "lastUpdateTime";


    @Pointcut("execution(* com.github.msa.*.dao.*.update*(..))")
    public void daoUpdate() {

    }

    @Pointcut("execution(* com.github.msa.*.dao.*.insert*(..))")
    public void daoCreate() {

    }

    @Around("daoUpdate()")
    public Object doAroundUodate(ProceedingJoinPoint pjp) throws  Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null == attributes) {
            return pjp.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        String userName = getUserName();
        if(null != token && null != userName) {
            Object[] objects = pjp.getArgs();
            if(null != objects && objects.length > 0) {
                for(Object arg : objects) {
                    BeanUtils.setProperty(arg, lastUpdateBy, userName);
                    BeanUtils.setProperty(arg, lastUpdateTime, new Date());
                }
            }
        }
        Object object = pjp.proceed();
        return object;
    }

    @Around("daoCreate()")
    public Object doAroundCreate(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null == attributes) {
            return pjp.proceed();
        }
        Object[] objects = pjp.getArgs();
        if(null != objects && objects.length > 0) {
            for(Object arg : objects) {
                String userName = getUserName();
                if(null != userName) {
                    if(StringUtils.isBlank(BeanUtils.getProperty(arg, createBy))) {
                        BeanUtils.setProperty(arg, createBy, userName);
                    }
                    if(StringUtils.isBlank(BeanUtils.getProperty(arg, createTime))) {
                        BeanUtils.setProperty(arg, createTime, new Date());
                    }
                }
            }
        }
        Object object = pjp.proceed();

        return object;
    }

    private String getUserName() {
        return SecurityUtils.getUsername();
    }
}
