package org.butu.config.security.component;

import cn.hutool.json.JSONUtil;
import org.butu.common.api.ApiResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: BTForum
 * @description: 自定义返回结果：未登录或登录过期
 * @packagename: org.butu.config.security.component
 * @author: BUTUbird
 * @date: 2022-03-15 16:45
 **/
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ApiResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
