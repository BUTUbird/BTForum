package org.butu.config.security.component;

import cn.hutool.json.JSONUtil;
import org.butu.common.api.ApiResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: BTForum
 * @description: 自定义返回结果：没有权限访问
 * @packagename: org.butu.config.security.component
 * @author: BUTUbird
 * @date: 2022-03-15 16:40
 **/
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ApiResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
