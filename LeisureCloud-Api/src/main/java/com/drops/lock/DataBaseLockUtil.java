package com.drops.lock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drops.controller.DataController;
import com.drops.mapper.GenericDao;
import com.drops.service.GenericService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @DESCRIPTION 基于数据库 乐观锁的 分布式锁
 *
 *   利用主键唯一的特性，如果有多个请求同时提交到数据库的话，
 *   数据库会保证只有一个操作可以成功，那么我们就可以认为操作成功的那个线程获得了该方法的锁，
 *   当方法执行完毕之后，想要释放锁的话，删除这条数据库记录即可。
 * @Author lst
 * @Date 2020-05-20 18:00
 */
@Component
public class DataBaseLockUtil {

    public Logger logger = Logger.getLogger(DataBaseLockUtil.class);
    @Autowired
    private GenericDao genericDao;

    @Scheduled(cron = "0 10 * * * ?")
    public void checkSynTaskKeyIsExpire() {
        logger.info("=========开始检查数据库分布式锁的过期时间=======");
        Map<String, Object> parems1 = new HashMap<>();
        List<LinkedHashMap<String, Object>> columns = this.genericDao.selectByParem("data_base_lock", "*", parems1);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(columns);
        List<DataBaseLock> taskLocks = jsonArray.toJavaList(DataBaseLock.class);
        //筛选出过期的key
        List<DataBaseLock> dataBaseLockList = taskLocks.stream().filter(new Predicate<DataBaseLock>() {
            @Override
            public boolean test(DataBaseLock dataBaseLock) {
                return System.currentTimeMillis() - dataBaseLock.getUpdateDate().getTime() - dataBaseLock.getTimeOut() * 1000 > 0;
            }
        }).collect(Collectors.toList());
        //删除过期的锁
        dataBaseLockList.forEach(new Consumer<DataBaseLock>() {
            @Override
            public void accept(DataBaseLock dataBaseLock) {
                Map<String, Object> parems = new HashMap<>();
                parems.put("id", dataBaseLock.getId());
                DataBaseLockUtil.this.genericDao.deleteByParem("data_base_lock", parems);
            }
        });
        logger.info("=========trs_task_lock 删除{"+dataBaseLockList.size()+"}条 过期的key======= ");
    }

    /**
     * 获取分布式锁
     * 根据插入语句中的主键冲突，相同主键的多次插入操作，只会有一次成功，成功的就获得分布式锁执行任务的权利
     * @author lst
     * @date 2020-5-21 8:53
     * @param key 锁名称
     * @param timeOut 锁超时时间
     * @return boolean
     */
    public boolean tryLock(String key,int timeOut){
        DataBaseLock dataBaseLock = new DataBaseLock();
        dataBaseLock.setLockKey(key);
        dataBaseLock.setTimeOut(timeOut);
        int flag = 0;
        try {
            Map<String,Object> parems = new HashMap<>();
            parems.put("id",dataBaseLock.getLockKey());
            parems.put("lock_key",dataBaseLock.getLockKey());
            parems.put("time_out",dataBaseLock.getTimeOut());
            flag = this.genericDao.insert("data_base_lock",parems);
            //flag = dataBaseLockMapper.insert(dataBaseLock);
        }catch (Exception e){
            logger.info("{"+key+"} 其他机器已经在执行");
            logger.info("错误信息：{"+e.toString()+"}");
        }
        return flag == 1;
    }

    /**
     * 删除分布式锁
     * @author lst
     * @date 2020-5-21 8:59
     * @param key 锁名称
     * @return boolean
     */
    public boolean delLock(String key){
        int flag = 0;
        try{
            Map<String, Object> parems = new HashMap<>();
            parems.put("lock_Key",key);
            this.genericDao.deleteByParem("data_base_lock",parems);
            //flag = dataBaseLockMapper.deleteAll(key);
        }catch (Exception e){
            logger.info("错误信息：{"+e.toString()+"}");
        }
        return flag == 1;
    }

    /**
     * 获取锁对象
     * @author lst
     * @date 2020-5-21 9:05
     * @param key
     * @return com.example.mybatiesplus.entity.DataBaseLock
     */
    public DataBaseLock getLock(String key){
        Map<String, Object> parems1 = new HashMap<>();
        parems1.put("lock_Key",key);
        List<LinkedHashMap<String, Object>> columns = this.genericDao.selectByParem("data_base_lock", "*", parems1);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(columns);
        List<DataBaseLock> taskLocks = jsonArray.toJavaList(DataBaseLock.class);
        return taskLocks.get(0);
    }

}
