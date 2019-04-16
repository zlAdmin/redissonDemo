package com.zl.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson的配置类
 *
 * @author zhanglei
 * @ProjectName: redissiontest
 * @create 2019-04-15 17:49
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    /** 对应的yml中的：clusters: 10.8.3.111:6379,10.8.3.111:6380,10.8.3.111:6381 */
//    @Value("${spring.redis.clusters}")
//    private  String cluster;

    @Bean
    public RedissonClient getRedisson(){
        RedissonClient redisson;
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);

        // 集群模式
        /*String[] nodes = cluster.split(",");
        for(int i=0;i<nodes.length;i++){
            nodes[i] = "redis://"+nodes[i];
        }
        config.useClusterServers().setScanInterval(2000).addNodeAddress(nodes).setPassword(password);*/
        redisson = Redisson.create(config);
        return redisson;
    }
}
