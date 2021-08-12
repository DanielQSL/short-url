package com.qsl.shorturl.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 短网址对象
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@Accessors(chain = true)
@Data
public class ShortUrlDTO {

    /**
     * 源链接地址
     */
    private String sourceUrl;

}
