package org.butu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.common.api.ApiResult;
import org.butu.model.vo.PostVO;
import org.butu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BUTU
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private PostService postService;
    @GetMapping
    public ApiResult<Page<PostVO>> searchList(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(results);
    }
}
