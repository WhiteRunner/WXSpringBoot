package com.xyy.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xyy.entity.User;
import com.xyy.utils.ResponseResult;
import com.xyy.pojo.WXloginRes;
import com.xyy.service.UserService;
import com.xyy.utils.QCloudUtils;
import com.xyy.utils.WeiXinLoginUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author WhiteRunner
 * @create 2021-11-11 18:20
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Resource
    private UserService service;
    @Resource
    private QCloudUtils utils;

    @PostMapping("/loadPic")
    public Object loadPic(@RequestBody JSONObject jsonObject) throws IOException {
        // 指定要上传的文件
        String picBase64 = jsonObject.getString("pic");
        byte[] bytes = Base64.decodeBase64(picBase64);
        File file = File.createTempFile("tmp", ".jpg");
        FileUtils.writeByteArrayToFile(file,bytes);
        //存储桶路径
        String key = "test/pic.jpg";
        //接收图片路径
        String url = utils.uploadPic(file, key);
        if(StringUtils.hasText(url)){
            HashMap<String, String> map = new HashMap<>();
            map.put("URL",url);
            return ResponseResult.success(map);
        }
        return ResponseResult.error("上传失败");

    }

    @GetMapping("/doLogin")
    public Object doLogin(String code){

        if (code==null){
            return ResponseResult.error("没有收到code");
        }
        //根据code获取用户openid
        WXloginRes loginResult = WeiXinLoginUtils.getLoginResult(code);
        if(loginResult.getErrCode()!=null) {
            return ResponseResult.error(loginResult,"code有误");
        }else {
            String openid = loginResult.getOpenid();
            //根据openId从数据库里找该用户
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getOpenid,openid);
            User user = service.getOne(wrapper);
            //如果user为null，将openId插入数据库
            if (user == null) {
                User newUser = new User();
                newUser.setOpenid(openid);
                service.save(newUser);
            }
            //根据openId生成token
            StpUtil.login(openid);
            //获取token的值保存到map中
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            HashMap<String, String> map = new HashMap<>();
            map.put("tokenName",tokenInfo.getTokenName());
            map.put("tokenValue",tokenInfo.getTokenValue());
            //封装返回值
            return ResponseResult.success(map,"登录成功");
        }

    }

    // 查询登录状态
    @GetMapping("/isLogin")
    public Object isLogin() {
        if(StpUtil.isLogin()){
            String loginId = StpUtil.getLoginIdAsString();
            System.out.println(loginId);
            return ResponseResult.success("已登录");
        }else {
            return ResponseResult.error("未登录");
        }
    }

    @GetMapping("/get")
    public String testGet(){
        return "get request success";
    }

    @PostMapping("/post")
    //JSONObject是map类的子类
    public String testPost(@RequestBody JSONObject jsonObject) {
        Integer num1 = jsonObject.getInteger("num1");
        Integer num2 = jsonObject.getInteger("num2");
        Integer res = num1+num2;
        return String.valueOf(num1)+" + "+String.valueOf(num2)+
                " = "+String.valueOf(res);
    }


}
