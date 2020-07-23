package com.drops.common.loadproperties;

import com.drops.tools.YmlUtils;

public class LoadProperties {
    public static void loadData(){
        /**
         * 思路
         * 读取配置文件信息到 properties 中
         * 数据库的信息采用 key - value 的设计，将数据库中的信息缓存到properties 中。
         * 或者在此处专门处理 读取数据库的 放入一个公用的类中。
         */
        try {
            YmlUtils configs = new YmlUtils();
            boolean b = configs.updateYaml("liang.aaa", "Intel Core 555555", "application.yml");
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
