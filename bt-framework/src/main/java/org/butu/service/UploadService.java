package org.butu.service;

import org.butu.common.api.ApiResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: BTForum
 * @description: 文件上传
 * @packagename: org.butu.service
 * @author: BUTUbird
 * @date: 2022-03-18 21:25
 **/
public interface UploadService {
    String upload(MultipartFile file);
}
