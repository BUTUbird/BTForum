package org.butu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.model.entity.Promotion;
import org.butu.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 广告推广表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Api(tags = "广告管理")
@RestController
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;
    @ApiOperation(value = "所有广告")
    @GetMapping("/list")
    public ApiResult<List<Promotion>>list(){
        List<Promotion> list = promotionService.list();
        return ApiResult.success(list);
    }

    /**
     * 后台管理 获取广告列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取广告列表")
    @RequestMapping("/getAll")
    public ApiResult<Page<Promotion>> getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
        Page<Promotion> promotionPage = promotionService.page(new Page<>(pageNo, pageSize));
        return ApiResult.success(promotionPage);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") Integer id)
    {
        promotionService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }
    @ApiOperation(value = "新增")
    @PostMapping("/insertOne")
    public ApiResult<String> insertOne(@RequestBody Promotion promotion)
    {
        promotionService.save(promotion);
        return ApiResult.success(null,"添加成功");
    }
}
