package com.xyy.pojo;

import lombok.Getter;

@Getter
public class WeixinLoginParams {
    private final String secret="1ceca6cafce4c3e98707e072b4e45e0b";
    private final String appid = "wxaa32462c024de275";
    private final String grantType="authorization_code";
}