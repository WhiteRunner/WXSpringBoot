package com.xyy.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author WhiteRunner
 * @create 2021-11-12 15:55
 */
@Component
public class QCloudUtils {

    COSClient cosClient = null;
    String bucketName="wxapp-1303032424";
    String baseURL="https://"+bucketName+".cos.ap-shanghai.myqcloud.com/";

    public QCloudUtils() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDJTOZT9SZ8PAYgLl4AzWjBMHxNt8BcZcP";
        String secretKey = "gFK9rbF1XUB5pdATZXHuxukV1IIAzGHD";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端
        cosClient = new COSClient(cred, clientConfig);
    }

    /**
     * @param file 要上传的文件
     * @param key 存储桶内路径 如 user/a.jpg
     * @return 文件地址
     */
    public String uploadPic(File file,String key){
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        cosClient.putObject(putObjectRequest);
        return baseURL+key;
    }


    /**
     * @param key 存储桶内路径 如 user/a.jpg
     */
    public void deletePic(String key){
        cosClient.deleteObject(bucketName, key);
    }


    //public boolean createBucket(String name) {
    //    //存储桶名称，格式：BucketName-APPID
    //    String bucket = name + "-1303032424";
    //    CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
    //    // 设置 bucket 的权限为 Private(私有读写)、其他可选有 PublicRead（公有读私有写）、PublicReadWrite（公有读写）
    //    createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
    //    try {
    //        Bucket bucketResult = cosClient.createBucket(createBucketRequest);
    //        cosClient.shutdown();
    //        return true;
    //    } catch (CosClientException serverException) {
    //        serverException.printStackTrace();
    //        return false;
    //    }
    //}

    //public List<Bucket> queryAllBucket() {
    //    List<Bucket> buckets = cosClient.listBuckets();
    //    //for (Bucket bucketElement : buckets) {
    //    //    String bucketName = bucketElement.getName();
    //    //    String bucketLocation = bucketElement.getLocation();
    //    //}
    //    cosClient.shutdown();
    //    return buckets;
    //}


}
