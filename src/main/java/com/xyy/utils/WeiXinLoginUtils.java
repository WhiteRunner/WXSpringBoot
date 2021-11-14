package com.xyy.utils;

import com.alibaba.fastjson.JSONObject;
import com.xyy.pojo.WXloginRes;
import com.xyy.pojo.WeixinLoginParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WhiteRunner
 * @create 2021-11-11 22:31
 */
public class WeiXinLoginUtils {
    public static WXloginRes getLoginResult(String code){

        RestTemplate restTemplate = new RestTemplate();
        WeixinLoginParams loginParams = new WeixinLoginParams();
        WXloginRes wXloginRes = new WXloginRes();

        // 封装请求参数
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type={grantType}";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appid", loginParams.getAppid());
        requestMap.put("secret", loginParams.getSecret());
        requestMap.put("grantType", loginParams.getGrantType());
        requestMap.put("code", code);

        // 发送请求，获得返回值
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class,requestMap);

        // 转换成json对象
        JSONObject jsonObject= JSONObject.parseObject(responseEntity.getBody());

        // 给返回对象wXloginRes赋值
        wXloginRes.setOpenid(jsonObject.getString("openid"));
        wXloginRes.setSessionKey(jsonObject.getString("session_key"));
        wXloginRes.setErrCode(jsonObject.getString("errcode"));
        wXloginRes.setErrMsg(jsonObject.getString("errmsg"));

        return wXloginRes;

    }
}
