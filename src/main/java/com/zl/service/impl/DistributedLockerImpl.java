package com.zl.service.impl;
import com.zl.service.DistributedLocker;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁实现
 *
 * @author zhanglei
 * @ProjectName: redissiontest
 * @create 2019-04-16 10:13
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
@Component
public class DistributedLockerImpl implements DistributedLocker {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    @Override
    public RLock lock(String lockKey, long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }

    @Override
    public RLock lock(String lockKey, TimeUnit unit, long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
          return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }

    @Override
    public RCountDownLatch countDownLatch(String key) {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(key);
        return countDownLatch;
    }

    @Override
    public RAtomicLong getAtomicLong(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        return atomicLong;
    }

    @Override
    public RFuture<Boolean> compareAndSetAsync(String key, Long oldValue, Long newValue) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        RFuture<Boolean> rFuture = atomicLong.compareAndSetAsync(oldValue, newValue);
        return rFuture;
    }

    @Override
    public RBloomFilter<String> getBloomFilter(String key) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(key);
        //初始化布隆过滤器 容量是5500000L，期望误差率为0.03
        //bloomFilter.tryInit(55000000L, 0.03);
        return bloomFilter;
    }

    @Override
    public void LongAdderAdd(String key) {
        RLongAdder longAdder = redissonClient.getLongAdder(key);
        longAdder.increment();
        //longAdder.decrement();
        //longAdder.destroy(); 销毁

        RBatch batch = redissonClient.createBatch();
    }
}
