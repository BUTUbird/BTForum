package org.butu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: BTForum
 * @description: 文件上传
 * @packagename: org.butu.controller
 * @author: BUTUbird
 * @date: 2022-03-18 21:21
 **/
@Api(tags = "上传")
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public ApiResult upload(@RequestPart MultipartFile file){
        String url = uploadService.upload(file);
        return ApiResult.success(url);
    }
}
