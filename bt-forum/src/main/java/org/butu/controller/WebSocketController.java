package org.butu.controller;

import com.alibaba.fastjson.JSON;
import org.butu.common.api.ApiResult;
import org.butu.config.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: BTForum
 * @description: 通知
 * @packagename: org.butu.controller
 * @author: BUTUbird
 * @date: 2022-04-14 22:59
 **/
@RestController
public class WebSocketController {
    @GetMapping("/pushToWeb")
    public ApiResult<?> push() {
        WebSocketServer.sendInfo(JSON.toJSONString(ApiResult.success("推送成功")),"10");
        return ApiResult.success("推送成功");
    }
}
