package com.xyy;

import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectResult;
import com.xyy.entity.User;
import com.xyy.mapper.UserMapper;
import com.xyy.service.UserService;
import com.xyy.utils.QCloudUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class WxSpringBootApplicationTests {
    @Resource
    private UserService service;
    @Resource
    private QCloudUtils utils;
    @Resource
    private StringRedisTemplate redis;

    @Test
    void TestRedis() {
        redis.opsForValue().set("key","value");
    }

    @Test
    void TestQCloud() throws IOException {
        utils.deletePic("test/pic.jpg");
    }

    @Test
    void TestMySql() {
        List<User> users = service.list();
        System.out.println(users);
    }

}
