package org.butu.config.security.component;

import cn.hutool.json.JSONUtil;
import org.butu.common.api.ApiResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: BTForum
 * @description: 自定义注销返回结果
 * @packagename: org.butu.config.security.component
 * @author: BUTUbird
 * @date: 2022-03-15 16:46
 **/
@Component
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ApiResult.success(null, "注销成功")));
        response.getWriter().flush();
    }
}
