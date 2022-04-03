package org.butu.service.Impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
import org.butu.service.UploadService;
import org.butu.utils.PathUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @program: BTForum
 * @description: 文件上传
 * @packagename: org.butu.service.Impl
 * @author: BUTUbird
 * @date: 2022-03-18 21:25
 **/
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;

    @Override
    public String upload(MultipartFile file) {
        //判断文件类型
        //获取原始文件名
        if (file == null){
            ApiAsserts.fail("文件上传失败！");
        }
        String originalFileName = file.getOriginalFilename();
        //对原始文件名进行判断
        if (!originalFileName.endsWith(".png") && !originalFileName.endsWith(".jpg") && !originalFileName.endsWith(".txt")){
            //TODO 抛出错误，此处只允许.png
            ApiAsserts.fail("只可以上传png格式的图片哦！");
        }
        String path = PathUtils.generateFilePath(originalFileName);
        return uploadOss(file,path);
    }

    private String uploadOss(MultipartFile file , String path) {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = path;
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return domain+key;
            } catch (QiniuException e) {
                Response r = e.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException e2) {

                }
            }
        } catch (Exception e) {

        }
        return "www";
    }
}
