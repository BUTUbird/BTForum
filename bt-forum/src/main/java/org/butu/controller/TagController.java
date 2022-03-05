package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.common.api.ApiResult;
import org.butu.model.entity.Post;
import org.butu.model.entity.Tag;
import org.butu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/{name}")
    public ApiResult<Map<String, Object>> getPostByTag(@PathVariable("name") String tagName,
                                                       @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size" ,defaultValue = "10") Integer size){
        Map<String, Object> map = new HashMap<>(16);

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, tagName);
        Tag one = tagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已被管理员删除");
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
}
