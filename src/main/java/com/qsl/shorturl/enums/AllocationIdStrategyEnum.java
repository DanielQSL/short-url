package com.qsl.shorturl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发号策略类型枚举
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@Getter
@AllArgsConstructor
public enum AllocationIdStrategyEnum {
    /**
     * 发号策略类型
     */
    REDIS("redis"),
    MYSQL("mysql"),
    ;

    private final String code;

}
