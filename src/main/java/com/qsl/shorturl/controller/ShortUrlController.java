package com.qsl.shorturl.controller;

import com.qsl.shorturl.common.CommonResponse;
import com.qsl.shorturl.dto.ShortUrlDTO;
import com.qsl.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短网址 Controller层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@RequestMapping("/short-url")
@RestController
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * 获取短网址
     *
     * @param url 网址
     * @return 短网址
     */
    @GetMapping("/getShortUrl")
    public CommonResponse<String> getShortUrl(@RequestParam("url") String url) {
        ShortUrlDTO shortUrlDTO = new ShortUrlDTO().setSourceUrl(url);
        return CommonResponse.success(shortUrlService.createShortUrl(shortUrlDTO));
    }

}
