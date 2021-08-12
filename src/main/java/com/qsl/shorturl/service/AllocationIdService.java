package com.qsl.shorturl.service;

/**
 * 发号器 服务层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
public interface AllocationIdService {

    /**
     * 生成ID
     *
     * @return ID
     */
    long generateId();

}
