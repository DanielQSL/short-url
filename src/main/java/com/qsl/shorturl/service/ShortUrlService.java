package com.qsl.shorturl.service;

import com.qsl.shorturl.dto.ShortUrlDTO;

/**
 * 短网址 服务层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
public interface ShortUrlService {

    /**
     * 创建短网址
     *
     * @param shortUrlDTO 短网址请求对象
     * @return 短网址
     */
    String createShortUrl(ShortUrlDTO shortUrlDTO);

    /**
     * 访问短网址
     *
     * @param shortUri 短链接
     * @return 原网址
     */
    String visitShortUrl(String shortUri);

}
