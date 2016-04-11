package com.cn.hash.server.zk.util;

/**
 * Created by 862911 on 2016/4/7.
 */
public class ZkConfig {
    private String ZkAddress;
    private String ZkPath;

    public String getZkAddress() {
        return ZkAddress;
    }

    public void setZkAddress(String zkAddress) {
        ZkAddress = zkAddress;
    }

    public String getZkPath() {
        return ZkPath;
    }

    public void setZkPath(String zkPath) {
        ZkPath = zkPath;
    }
}
