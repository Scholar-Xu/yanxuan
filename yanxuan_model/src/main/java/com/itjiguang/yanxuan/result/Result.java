package com.itjiguang.yanxuan.result;

import java.io.Serializable;

/**
 * 完成响应结果的封装
 */
public class Result implements Serializable {

    private boolean code;// 如果成功返回true；如果失败返回false

    private String message;// 描述信息

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
