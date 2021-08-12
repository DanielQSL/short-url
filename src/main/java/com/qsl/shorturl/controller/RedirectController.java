package com.qsl.shorturl.controller;

import com.qsl.shorturl.common.CommonResponse;
import com.qsl.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 重定向 Controller层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@RequestMapping("/")
@Controller
public class RedirectController {

    @Autowired
    private ShortUrlService shortUrlService;

    private static final String FAKE_LONG_URL = "/test?text=welcome long url [%s]";

    /**
     * 访问短网址
     *
     * @param shortUri 短链接
     * @return 重定向
     */
    @GetMapping("/{shortUri}")
    public String visitShortUrl(@PathVariable("shortUri") String shortUri) {
        String targetUri = shortUrlService.visitShortUrl(shortUri);
        // todo 需重定向到真实地址，此处测试使用
        // 在返回的字符串前面添加 redirect: 方式来告诉Spring框架，需要做302重定向处理
        return "redirect:" + String.format(FAKE_LONG_URL, targetUri);
    }

    /**
     * 测试重定向
     *
     * @param text 文本
     * @return 响应结果
     */
    @ResponseBody
    @GetMapping("/test")
    public CommonResponse<String> test(@RequestParam("text") String text) {
        return CommonResponse.success(text);
    }

}
