package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.butu.common.api.ApiResult;
import org.butu.model.entity.Billboard;
import org.butu.model.vo.CountVO;
import org.butu.service.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 全站公告 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */

@Api(tags = "公告管理")
@RestController
@RequestMapping("/billboard")
public class BillboardController {

    @Autowired
    private BillboardService billboardService;

    @ApiOperation(value = "公告查询")
    @GetMapping("/show")
    public ApiResult<Billboard> getNotices(){
        List<Billboard> list = billboardService.list(new
                LambdaQueryWrapper<Billboard>().eq(Billboard::isShows,true));
        return ApiResult.success(list.get(list.size()-1));
    }

    /**
     * 后台 公告
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "获取所有")
    @GetMapping("/getAll")
    public ApiResult<Page<Billboard>>getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                            @RequestParam(value = "size", defaultValue = "10") Integer pageSize){
        Page<Billboard> billboards= billboardService.page(new Page<>(pageNo, pageSize));
        return ApiResult.success(billboards);
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "删除")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") Integer id)
    {
        billboardService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "禁用")
    @RequestMapping("/disableOne/{id}")
    public ApiResult<String> disableOne(@PathVariable("id") Integer id)
    {
        Billboard billboard = billboardService.getById(id);
        billboard.setShows(false);
        billboardService.updateById(billboard);
        return ApiResult.success(null,"禁用成功");
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "显示")
    @PostMapping("/ableOne/{id}")
    public ApiResult<String> ableOne(@PathVariable("id") Integer id)
    {
        Billboard billboard = billboardService.getById(id);
        billboard.setShows(true);
        billboardService.updateById(billboard);
        return ApiResult.success(null,"已启用");
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "新增")
    @PostMapping("/insertOne/{data}")
    public ApiResult<String> insertOne(@PathVariable("data") String content)
    {
        billboardService.insertOne(content);
        return ApiResult.success(null,"添加成功");
    }
    @ApiOperation(value = "网站统计")
    @GetMapping("/count")
    public ApiResult<CountVO> statistics(){
        CountVO count = billboardService.statistics();
        return ApiResult.success(count);
    }
}
