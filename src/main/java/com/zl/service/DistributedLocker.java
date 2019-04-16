package com.zl.service;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;

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
}
