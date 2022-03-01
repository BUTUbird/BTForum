package org.butu.controller;


import org.butu.common.api.ApiResult;
import org.butu.model.entity.Promotion;
import org.butu.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 广告推广表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/list")
    public ApiResult<List<Promotion>>list(){
        List<Promotion> list = promotionService.list();
        return ApiResult.success(list);
    }
}
