package com.itjiguang.yanxuan.seller.controller;

import com.itjiguang.yanxuan.utils.FastDFSClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @PostMapping
    public ResponseEntity uploadFile(MultipartFile[] file){
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        System.out.println("上传文件的个数："+file.length);
        // 创建FastDFS的工具类
        FastDFSClient client = new FastDFSClient("classpath:/fastdfs/client.properties");
        for (MultipartFile tmpFile : file ) {
            String originalFilename = tmpFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            try {
                // http://192.168.142.128/group1/M00.......
                String path = client.uploadFile(tmpFile.getBytes(), extName);
                list.add(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 设置返回的标识，0表示成功
        map.put("errno","0");
        map.put("data", list);

        return new ResponseEntity(map, HttpStatus.OK);
    }
}
