package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
import org.butu.mapper.WordMapper;
import org.butu.model.entity.Promotion;
import org.butu.model.entity.Word;
import org.butu.service.UploadService;
import org.butu.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * <p>
 * 敏感词库 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-02
 */
@Api(tags = "违禁词管理")
@RestController
@RequestMapping("/word")
public class WordController {
    @Autowired
    private WordService wordService;
    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private UploadService uploadService;
    /**
     * 后台管理 获取广告列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取违禁词列表")
    @GetMapping("/getAll")
    public ApiResult<Page<Word>> getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
        Page<Word> wordPage = wordService.page(new Page<>(pageNo, pageSize));
        return ApiResult.success(wordPage);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") Integer id)
    {
        wordService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }
    @ApiOperation(value = "新增")
    @PostMapping("/insertOne")
    public ApiResult<String> insertOne(@RequestBody Word word)
    {
        if (word.getWord() != null && !Objects.equals(word.getWord(), "")) {
            LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Word::getWord, word.getWord());
            Word word1 = wordMapper.selectOne(queryWrapper);
            if (word1 == null) {
                wordService.save(word);
            } else {
                System.out.println(word.getWord() + "已存在");
            }
        }else {
            ApiAsserts.fail("请输入违禁词");
        }
        return ApiResult.success(null,"添加成功");
    }

    @ApiOperation(value = "文本方式新增")
    @PostMapping("/insertTxT")
    public ApiResult<String> insertTxT(@RequestPart MultipartFile file){
        String path = uploadService.upload(file);
        wordService.insertTxt(path);
        return ApiResult.success("插入成功");
    }
}
