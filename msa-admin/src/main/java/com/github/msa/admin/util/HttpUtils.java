package com.github.msa.admin.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.github.msa.core.http.HttpResult;

/**
 * HTTP util class
 * @author Silent
 * @date Oct 29, 2018
 */
public class HttpUtils {
    /**
     * get HttpServletRequest Object
     * @return HttpServletRequest Object
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * print message into browser
     * @param response
     * @param code
     * @param message
     * @throws IOException
     */
    public static void print(HttpServletResponse response, int code, String message) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpResult result = HttpResult.error(code, message);
        String json = JSONObject.toJSONString(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
