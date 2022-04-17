package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
import org.butu.model.entity.Post;
import org.butu.model.entity.Tag;
import org.butu.model.vo.TagVO;
import org.butu.service.TagService;
import org.butu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@RestController
@Api(tags = "标签管理")
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "获取文章标签")
    @GetMapping("/{name}")
    public ApiResult<Map<String, Object>> getPostByTag(@PathVariable("name") String tagName,
                                                       @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size" ,defaultValue = "10") Integer size){
        Map<String, Object> map = new HashMap<>(16);

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, tagName);
        Tag one = tagService.getOne(wrapper);
        if (Objects.isNull(one)){
            ApiAsserts.fail("话题不存在，或已被管理员删除");
        }
        Page<Post> topics = tagService.selectPostByTagId(new Page<>(page, size), one.getId());
        // 其他热门标签
        Page<Tag> hotTags = tagService.page(new Page<>(1, 10),
                new LambdaQueryWrapper<Tag>()
                        .notIn(Tag::getName, tagName)
                        .orderByDesc(Tag::getTopicCount));

        map.put("topics", topics);
        map.put("hotTags", hotTags);

        return ApiResult.success(map);
    }

    @ApiOperation(value = "板块推荐")
    @GetMapping("/recommendTags")
    public ApiResult<List<TagVO>> getAllTags() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Tag::getTopicCount);
        List<Tag> list = tagService.list(wrapper);
        List<TagVO> vo = BeanCopyUtils.copyBeanList(list, TagVO.class);
        return ApiResult.success(vo);
    }


    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "获取所有标签")
    @RequestMapping("/getAll")
    public ApiResult<Page<Tag>> getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                          @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
        Page<Tag> tagPage = tagService.page(new Page<>(pageNo, pageSize));
        return ApiResult.success(tagPage);
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "删除标签")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") String id)
    {
        tagService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }
}
