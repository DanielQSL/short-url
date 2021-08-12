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

    /**
     * 保存长短链接映射关系
     *
     * @param shortUri 短链接
     * @param longUrl  原链接（长链接）
     * @return 是否保存成功
     */
    public abstract boolean saveLongAndShotUrlMap(String shortUri, String longUrl);

    /**
     * 根据短链接获取原网址
     *
     * @param shortUri 短链接
     * @return 原网址
     */
    public abstract String getSourceUrlByUri(String shortUri);

}
