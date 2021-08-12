package com.qsl.shorturl.service.storage;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 发号器存储工厂
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
public class AllocationIdStorageFactory {

    private static final Map<String, AbstractAllocationIdStorage> STRATEGY_MAP = Maps.newHashMap();

    /**
     * 获取发号器存储
     *
     * @param code 发号器存储编号
     * @return 具体发号器存储
     */
    public static AbstractAllocationIdStorage getInvokeStrategy(String code) {
        return STRATEGY_MAP.get(code);
    }

    /**
     * 将发号器存储注册到工厂中
     *
     * @param code     发号器存储编号
     * @param strategy 发号器存储
     */
    public static void register(String code, AbstractAllocationIdStorage strategy) {
        if (null == strategy) {
            return;
        }
        STRATEGY_MAP.put(code, strategy);
    }

}