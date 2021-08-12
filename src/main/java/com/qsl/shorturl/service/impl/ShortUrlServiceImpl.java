package com.qsl.shorturl.service.impl;

import com.qsl.shorturl.constants.CommonConstant;
import com.qsl.shorturl.dto.ShortUrlDTO;
import com.qsl.shorturl.enums.AllocationIdStrategyEnum;
import com.qsl.shorturl.service.AllocationIdService;
import com.qsl.shorturl.service.ShortUrlService;
import com.qsl.shorturl.service.storage.AbstractAllocationIdStorage;
import com.qsl.shorturl.service.storage.AllocationIdStorageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短网址 服务实现层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@Slf4j
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    /**
     * 在进制表示中的字符集合
     */
    private final static char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Autowired
    private AllocationIdService allocationIdService;

    @Override
    public String createShortUrl(ShortUrlDTO shortUrlDTO) {
        // 生成一个id
        long shortUrlId = allocationIdService.generateId();
        // 将数字转换为62进制字符
        String shortUri = to62BaseStr(shortUrlId);
        // 保存长短链接映射关系
        AbstractAllocationIdStorage allocationIdStrategy = AllocationIdStorageFactory.getInvokeStrategy(AllocationIdStrategyEnum.REDIS.getCode());
        allocationIdStrategy.saveLongAndShortMap(shortUri, shortUrlDTO.getSourceUrl());
        return CommonConstant.SHORT_URL_PREFIX + shortUri;
    }

    /**
     * 将10进制的数字转换到62进制字符
     *
     * @param num 数字
     * @return 62进制字符
     */
    private String to62BaseStr(long num) {
        return toOtherBaseStr(num, 62);
    }

    /**
     * 将10进制的数字转换到其他进制字符
     *
     * @param num  数字
     * @param base 多少进制
     * @return 62进制字符
     */
    private String toOtherBaseStr(long num, int base) {
        int charPos = 32;
        char[] buf = new char[32];
        while ((num / base) > 0) {
            buf[--charPos] = DIGITS[(int) (num % base)];
            num /= base;
        }
        buf[--charPos] = DIGITS[(int) (num % base)];
        return new String(buf, charPos, (32 - charPos));
    }

    @Override
    public String visitShortUrl(String shortUri) {
        AbstractAllocationIdStorage allocationIdStrategy = AllocationIdStorageFactory.getInvokeStrategy(AllocationIdStrategyEnum.REDIS.getCode());
        return allocationIdStrategy.getSourceUrl(shortUri);
    }

}
