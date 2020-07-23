package com.drops.common.listener;

import com.drops.tools.YmlUtil;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ZookListener {
    private ZooKeeper zooKeeper;

    private List<String> paths = new LinkedList<>();


    public void init() {
        try {
            String server = (String) YmlUtil.getValue("application.yml", "zookeeper.server");
            int zktimeout = (int) YmlUtil.getValue("application.yml", "zookeeper.zktimeout");
            String serverPath = (String) YmlUtil.getValue("application.yml", "zookeeper.serverpath");
            zooKeeper = new ZooKeeper(server, zktimeout, new Watcher() {
                @Override
                public void process(WatchedEvent WatchEvent) {
                    //监听该节点的变化，如果节点出现变化，则重新获取节点下的ip和端口
                    if (WatchEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged && WatchEvent.getPath().equals(serverPath)) {
                        ZookListener.this.getChilds();
                    }

                }
            });
            getChilds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<String> getChilds() {
        List<String> ips = new LinkedList<>();
        try {
            //添加监听
            String serverPath = (String) YmlUtil.getValue("application.yml", "zookeeper.serverpath");
            List<String> childs = this.zooKeeper.getChildren(serverPath, true);
            for (String child : childs) {
                byte[] obj = zooKeeper.getData(serverPath + "/" + child, false, null);
                String path = new String(obj, "utf-8");
                ips.add(path);
            }
            this.paths = ips;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.paths;
    }


    public String getPath() {
        if (paths.isEmpty()) {
            return null;
        }
        //这里我们随机获取一个ip端口使用
        int index = new Random().nextInt(paths.size());
        return paths.get(index);
    }

}


