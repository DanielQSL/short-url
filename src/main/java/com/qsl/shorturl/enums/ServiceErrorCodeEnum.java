package com.qsl.shorturl.enums;

import com.qsl.shorturl.common.BaseCommonError;

/**
 * 短网址服务错误码
 * <p>
 * 错误码区间 [401_00_0000 ~ 402_00_0000)
 *
 * @author DanielQSL
 */
public enum ServiceErrorCodeEnum implements BaseCommonError {

    // ========== 发号 ==========
    GENERATE_ID_FAILED(401_01_0000, "生成ID失败"),

    ;

    private final Integer code;
    private final String msg;

    ServiceErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public int getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }

}
