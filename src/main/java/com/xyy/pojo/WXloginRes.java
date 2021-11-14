package com.xyy.pojo;

import lombok.Data;
import lombok.ToString;

@Data
public class WXloginRes {
    private String openid;
    private String sessionKey;
    private String errCode;
    private String errMsg;
}