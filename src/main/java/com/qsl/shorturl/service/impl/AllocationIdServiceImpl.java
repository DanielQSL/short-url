package com.qsl.shorturl.service.impl;

import com.qsl.shorturl.enums.AllocationIdStrategyEnum;
import com.qsl.shorturl.service.AllocationIdService;
import com.qsl.shorturl.service.storage.AbstractAllocationIdStorage;
import com.qsl.shorturl.service.storage.AllocationIdStorageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发号器 服务实现层
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@Slf4j
@Service
public class AllocationIdServiceImpl implements AllocationIdService {

    @Override
    public Long generateId() {
        AbstractAllocationIdStorage allocationIdStrategy = AllocationIdStorageFactory.getInvokeStrategy(AllocationIdStrategyEnum.REDIS.getCode());
        return allocationIdStrategy.generateId();
    }

}
