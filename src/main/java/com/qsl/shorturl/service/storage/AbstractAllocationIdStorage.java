package com.qsl.shorturl.service.storage;

import org.springframework.beans.factory.InitializingBean;

/**
 * 发号器存储抽象类
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
public abstract class AbstractAllocationIdStorage implements InitializingBean {

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
    public abstract boolean saveLongAndShortMap(String shortUri, String longUrl);

    /**
     * 获取原网址
     *
     * @param shortUri 短链接
     * @return 原网址
     */
    public abstract String getSourceUrl(String shortUri);

    /**
     * 清除短链接
     *
     * @param shortUri 短链接
     */
    public abstract void cleanShortUri(String shortUri);

}
