package com.qsl.shorturl.service.storage;

import com.qsl.shorturl.enums.AllocationIdStrategyEnum;
import com.qsl.shorturl.enums.ServiceErrorCodeEnum;
import com.qsl.shorturl.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于Redis的发号策略
 * 单机可支撑 10 w+ 请求，足以应付大部分的业务场景
 * 如果一台机器扛不住，可以设置多台嘛，比如布置 10 台机器，每台机器分别只生成尾号0，1，2，... 9 的 ID, 每次加 10 即可，只要设置一个 ID 生成器代理随机分配给发号器生成 ID 就行了。
 * 需要考虑持久化（短链ID总不能一样吧），灾备，成本有点高
 *
 * @author qianshuailong
 * @date 2021/8/12
 */
@Component
public class AllocationIdStorageRedis extends AbstractAllocationIdStorage {

    private static final String SHORT_URL_SEED = "short_url_seed";

    private static final String SHORT_LONG_MAP_PREFIX = "short_long_map:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public long generateId() {
        Long shortUrlSeed = redisTemplate.opsForValue().increment(SHORT_URL_SEED);
        if (shortUrlSeed == null) {
            throw new BizException(ServiceErrorCodeEnum.GENERATE_ID_FAILED);
        }
        return shortUrlSeed;
    }

    @Override
    public boolean saveLongAndShortMap(String shortUri, String longUrl) {
        redisTemplate.opsForValue().set(SHORT_LONG_MAP_PREFIX + shortUri, longUrl, 24, TimeUnit.HOURS);
        return true;
    }

    @Override
    public String getSourceUrl(String shortUri) {
        String sourceUrl = (String) redisTemplate.opsForValue().get(SHORT_LONG_MAP_PREFIX + shortUri);
        if (StringUtils.isBlank(sourceUrl)) {
            throw new BizException(ServiceErrorCodeEnum.VISIT_URL_NOT_EXIST);
        }
        return sourceUrl;
    }

    @Override
    public void cleanShortUri(String shortUri) {
        redisTemplate.delete(SHORT_LONG_MAP_PREFIX + shortUri);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AllocationIdStorageFactory.register(AllocationIdStrategyEnum.REDIS.getCode(), this);
    }

}
