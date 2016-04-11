package com.cn.hash.server.zk;

import com.cn.hash.server.zk.util.ZkConfig;
import com.cn.hash.server.zk.util.ZkConnector;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 862911 on 2016/4/7.
 */
@Component
public class ZkInitializer implements InitializingBean{
    @Autowired
    private ZkConfig zkConfig;

    public void afterPropertiesSet() throws Exception {
        ZkConnector.connect(zkConfig.getZkAddress());
        ZooKeeper zooKeeper = ZkConnector.getConnection();
        assert zooKeeper != null;
        zooKeeper.create(zkConfig.getZkPath(),"server-zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }
}
