package com.zl.service;

import org.redisson.api.*;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁接口
 *
 * @author zhanglei
 * @ProjectName: redissiontest
 * @create 2019-04-16 10:12
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public interface DistributedLocker {
    RLock lock(String lockKey);

    RLock lock(String lockKey, long timeout);

    RLock lock(String lockKey, TimeUnit unit, long timeout);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);

    RCountDownLatch countDownLatch(String key);

    RAtomicLong getAtomicLong(String key);

    RFuture<Boolean> compareAndSetAsync(String key, Long oldValue, Long newValue);

    RBloomFilter<String> getBloomFilter(String key);
    
    /**
     * @Description 长整型加   速度比分布式AtomicLong快1200倍
     * @return void
     * @throws 
     * @Author zhanglei
     * @Date 14:50 2019/4/16
     * @Param [key]
     **/
    void LongAdderAdd(String key);
}
