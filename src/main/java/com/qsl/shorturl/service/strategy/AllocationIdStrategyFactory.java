package com.qsl.shorturl.service.strategy;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 发号策略工厂
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
public class AllocationIdStrategyFactory {

    private static final Map<String, AbstractAllocationIdStrategy> STRATEGY_MAP = Maps.newHashMap();

    /**
     * 获取发号策略
     *
     * @param code 发号策略编号
     * @return 具体发号策略
     */
    public static AbstractAllocationIdStrategy getInvokeStrategy(String code) {
        return STRATEGY_MAP.get(code);
    }

    /**
     * 将发号策略注册到工厂中
     *
     * @param code     发号策略编号
     * @param strategy 发号策略
     */
    public static void register(String code, AbstractAllocationIdStrategy strategy) {
        if (null == strategy) {
            return;
        }
        STRATEGY_MAP.put(code, strategy);
    }

}