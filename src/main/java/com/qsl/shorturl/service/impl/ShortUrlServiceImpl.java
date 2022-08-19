package com.qsl.shorturl.service.impl;

import com.qsl.shorturl.constants.CommonConstant;
import com.qsl.shorturl.dto.ShortUrlDTO;
import com.qsl.shorturl.enums.AllocationIdStrategyEnum;
import com.qsl.shorturl.service.AllocationIdService;
import com.qsl.shorturl.service.ShortUrlService;
import com.qsl.shorturl.service.storage.AbstractAllocationIdStorage;
import com.qsl.shorturl.service.storage.AllocationIdStorageFactory;
import com.qsl.shorturl.utils.BaseConvertUtil;
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

    @Autowired
    private AllocationIdService allocationIdService;

    @Override
    public String createShortUrl(ShortUrlDTO shortUrlDTO) {
        // 生成一个id
        Long shortUrlId = allocationIdService.generateId();
        // 将数字转换为62进制字符
        String shortUri = BaseConvertUtil.to62BaseStr(shortUrlId);
        // 保存长短链接映射关系
        AbstractAllocationIdStorage allocationIdStrategy = AllocationIdStorageFactory.getInvokeStrategy(AllocationIdStrategyEnum.REDIS.getCode());
        allocationIdStrategy.saveLongAndShortMap(shortUri, shortUrlDTO.getSourceUrl());
        return CommonConstant.SHORT_URL_PREFIX + shortUri;
    }

    @Override
    public String visitShortUrl(String shortUri) {
        AbstractAllocationIdStorage allocationIdStrategy = AllocationIdStorageFactory.getInvokeStrategy(AllocationIdStrategyEnum.REDIS.getCode());
        return allocationIdStrategy.getSourceUrl(shortUri);
    }

}
