package com.qsl.shorturl.service.impl;

import com.qsl.shorturl.enums.AllocationIdStrategyEnum;
import com.qsl.shorturl.service.AllocationIdService;
import com.qsl.shorturl.service.strategy.AbstractAllocationIdStrategy;
import com.qsl.shorturl.service.strategy.AllocationIdStrategyFactory;
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
    public long generateId() {
        AbstractAllocationIdStrategy allocationIdStrategy = AllocationIdStrategyFactory.getInvokeStrategy(AllocationIdStrategyEnum.REDIS.getCode());
        return allocationIdStrategy.generateId();
    }

}
