package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
import org.butu.model.entity.Follow;
import org.butu.model.entity.User;
import org.butu.service.FollowService;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;



/**
 * @author BUTU
 */
@RestController
@RequestMapping("/relationship")
public class RelationshipController{
@Autowired
private UserService userService;
@Autowired
private FollowService followService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/validate/{topicUserId}")
    public ApiResult<Map<String, Object>> isFollow(@PathVariable("topicUserId") String topicUserId, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(user)) {
            Follow one = followService.getOne(new LambdaQueryWrapper<Follow>()
                    .eq(Follow::getParentId, topicUserId)
                    .eq(Follow::getFollowerId, user.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return ApiResult.success(map);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/subscribe/{userId}")
    public ApiResult<Object> handleFollow(@PathVariable("userId") String parentId,Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        if (parentId.equals(user.getId())) {
            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢 😮");
        }
        Follow one = followService.getOne(
                new LambdaQueryWrapper<Follow>()
                        .eq(Follow::getParentId, parentId)
                        .eq(Follow::getFollowerId, user.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("已关注");
        }

        Follow follow = new Follow();
        follow.setParentId(parentId);
        follow.setFollowerId(user.getId());
        followService.save(follow);
        return ApiResult.success(null, "关注成功");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/unsubscribe/{userId}")
    public ApiResult<Object> handleUnFollow(@PathVariable("userId") String parentId,Principal principal) {
        User umsUser = userService.getUserByUsername(principal.getName());
        Follow one = followService.getOne(
                new LambdaQueryWrapper<Follow>()
                        .eq(Follow::getParentId, parentId)
                        .eq(Follow::getFollowerId, umsUser.getId()));
        if (ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("未关注！");
        }
        followService.remove(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, parentId)
                .eq(Follow::getFollowerId, umsUser.getId()));
        return ApiResult.success(null, "取关成功");
    }
}
