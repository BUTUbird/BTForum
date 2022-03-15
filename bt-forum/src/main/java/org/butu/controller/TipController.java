package org.butu.controller;


import org.butu.common.api.ApiResult;
import org.butu.model.entity.Tip;
import org.butu.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 每日赠言 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/tip")
public class TipController {
    @Autowired
    private TipService tipService;


    @GetMapping("/today")
    public ApiResult<Tip> getRandomTip() {
        Tip tip = tipService.getRandomTip();
        return ApiResult.success(tip);
    }
}
