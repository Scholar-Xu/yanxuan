package com.itjiguang.yanxuan.test;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSClientTest {

    public static void main(String[] args) throws IOException, MyException {
        // 加载全局的配置文件
        ClientGlobal.init("D:\\idea_workspace\\jiguang-project\\yanxuan_parent\\yanxuan_seller_server\\src\\main\\resources\\fastdfs\\client.properties");
        // 创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        // 连接TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        // 获取StorageServer
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        // 创建StorageClient
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 上传文件
        /**
         * 第一个：本地文件的名称，上传文件的名称
         * 第二个：文件的扩展名
         * 第三个：描述信息
         */
        String[] resultList = storageClient.upload_file("C:\\Users\\SCHOLAR\\Desktop\\极光学苑\\极光严选\\day05-商品录入\\资料\\素材\\626451a220d4df4a.jpg", "jpg", null);

        for (String str : resultList  ) {
            System.out.println(str);
        }
    }
}
