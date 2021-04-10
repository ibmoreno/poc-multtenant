package com.idcotton.app.config.multitenant;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultiTenantInterceptor extends HandlerInterceptorAdapter {

    public static final String TENANT_HEADER_NAME = "X-TENANT-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String tenantId = request.getHeader(TENANT_HEADER_NAME);

        if (StringUtils.isEmpty(tenantId)) {
            response.getWriter().write("X-TENANT-ID nao presente no Request Header");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }

        MultiTenantContext.setTenantId(tenantId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        MultiTenantContext.clear();
    }

}
