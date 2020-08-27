package com.itjiguang.yanxuan.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * FastDFS文件存储工具类
 */
public class FastDFSClient {
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;
    private String hostName = null;

    public FastDFSClient(String conf) {
        // 处理配置文件的路径
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }

        try {
            // 加载配置信息
            ClientGlobal.init(conf);
            // 创建TrackerClient
            trackerClient = new TrackerClient();
            // 建立连接，获取TrackerServer
            trackerServer = trackerClient.getConnection();
            // 根据TrackerServer获取StorageServer
            storageServer = trackerClient.getStoreStorage(trackerServer);
            // 设置hostname
            hostName = storageServer.getInetSocketAddress().getHostName();
        } catch (MyException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        // 根据TrackerServer和StorageServer获取StorageClient，可以用来完成文件的上传
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件
     * @param fileName 文件全路径
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) {
        String result=null;
        try {
            result = storageClient.upload_file1(fileName, extName, metas);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http://"+hostName+"/"+result;
    }

    public String uploadFile(String fileName){
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件
     * @param fileContent 文件内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas){

        String result = null;
        try {
            result = storageClient.upload_file1(fileContent, extName, metas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http://"+hostName+"/"+result;
    }

    public String uploadFile(byte[] fileContent) {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) {
        return uploadFile(fileContent, extName, null);
    }
}