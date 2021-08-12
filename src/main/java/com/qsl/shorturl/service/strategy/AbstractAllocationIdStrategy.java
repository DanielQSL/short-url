package com.qsl.shorturl.service.strategy;

import org.springframework.beans.factory.InitializingBean;

/**
 * 发号策略 抽象层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
public abstract class AbstractAllocationIdStrategy implements InitializingBean {

    /**
     * 生成ID
     *
     * @return ID
     */
    public abstract long generateId();

}
