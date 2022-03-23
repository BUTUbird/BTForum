package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.model.dto.CarouselDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.Billboard;
import org.butu.model.entity.Carousel;
import org.butu.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 轮播图 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-23
 */
@Api(tags = "轮播图管理")
@RestController
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @ApiOperation(value = "查询")
    @GetMapping("/show")
    public ApiResult<List<Carousel>> getNotices(){
        List<Carousel> list = carouselService.list(new
                LambdaQueryWrapper<Carousel>().eq(Carousel::isShows,true));
        return ApiResult.success(list);
    }


    @ApiOperation(value = "获取所有")
    @GetMapping("/getAll")
    public ApiResult<Page<Carousel>>getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                            @RequestParam(value = "size", defaultValue = "10") Integer pageSize){
        Page<Carousel> carousel= carouselService.page(new Page<>(pageNo, pageSize));
        return ApiResult.success(carousel);
    }
    @ApiOperation(value = "删除")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") Integer id)
    {
        carouselService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }

    @ApiOperation(value = "禁用")
    @RequestMapping("/disableOne/{id}")
    public ApiResult<String> disableOne(@PathVariable("id") Integer id)
    {
        Carousel carousel = carouselService.getById(id);
        carousel.setShows(false);
        carouselService.updateById(carousel);
        return ApiResult.success(null,"禁用成功");
    }
    @ApiOperation(value = "显示")
    @PostMapping("/ableOne/{id}")
    public ApiResult<String> ableOne(@PathVariable("id") Integer id)
    {
        Carousel carousel = carouselService.getById(id);
        carousel.setShows(true);
        carouselService.updateById(carousel);
        return ApiResult.success(null,"已启用");
    }
    @ApiOperation(value = "新增")
    @PostMapping("/insertOne")
    public ApiResult<String> insertOne(@Valid @RequestBody CarouselDTO dto)
    {
        carouselService.insertOne(dto);
        return ApiResult.success(null,"添加成功");
    }
}
