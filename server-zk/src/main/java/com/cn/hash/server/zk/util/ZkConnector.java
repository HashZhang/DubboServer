package com.cn.hash.server.zk.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 862911 on 2016/4/7.
 */
public class ZkConnector {
    private static ZooKeeper zooKeeper;
    private static CountDownLatch countDownLatch;
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkConnector.class);

    public static void connect(String host) {
        try {
            countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(host, 5000, new Watcher() {
                public void process(WatchedEvent event) {
                    //直到连接成功，才countdown
                    if (event.getState() != null && event.getState() == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                    }
                }
            });
            //连接成功，才会返回
            countDownLatch.await();
        } catch (IOException e) {
            LOGGER.error(e.toString());
        } catch (InterruptedException e) {
            LOGGER.warn(e.toString());
        }
    }

    public static ZooKeeper getConnection() {
        if (zooKeeper != null && zooKeeper.getState() == ZooKeeper.States.CONNECTED) {
            return zooKeeper;
        } else {
            LOGGER.error("ZK Connection is not ready!");
            return null;
        }
    }
}
