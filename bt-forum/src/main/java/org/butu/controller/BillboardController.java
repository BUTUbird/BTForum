package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.common.api.ApiResult;
import org.butu.model.entity.Billboard;
import org.butu.service.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 全站公告 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/billboard")
public class BillboardController {

    @Autowired
    private BillboardService billboardService;

    @GetMapping("/show")
    public ApiResult<Billboard> getNotices(){
        List<Billboard> list = billboardService.list(new
                LambdaQueryWrapper<Billboard>().eq(Billboard::isShows,true));
        return ApiResult.success(list.get(list.size()-1));
    }
}
