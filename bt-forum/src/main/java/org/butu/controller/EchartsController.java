package org.butu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.mapper.EchartsMapper;
import org.butu.model.vo.TagVO;
import org.butu.model.vo.p_echartsVO;
import org.butu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: BTForum
 * @description: Echarts图表
 * @packagename: org.butu.controller
 * @author: BUTUbird
 * @date: 2022-03-24 21:39
 **/
@Api(tags = "图表管理")
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private TagService tagService;
    @Autowired
    private EchartsMapper echartsMapper;

    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "标签数据图")
    @GetMapping("/getTag")
    public ApiResult<List<TagVO>> getTag(){
        List<TagVO> tag = tagService.getTag();
        return ApiResult.success(tag);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "用户发帖数据图")
    @GetMapping("/getPostsByUser")
    public ApiResult<List<p_echartsVO>> getPosts(){
        return ApiResult.success(echartsMapper.selectPostsByUser());
    }

}
