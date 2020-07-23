package com.drops.tools;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;

@Component
public class YmlUtils {

    private final static DumperOptions OPTIONS = new DumperOptions();

    static{
        //设置yaml读取方式为块读取
        OPTIONS.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        OPTIONS.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        OPTIONS.setPrettyFlow(false);
    }

    /**
     * 将yaml配置文件转化成map
     * fileName 默认是resources目录下的yaml文件, 如果yaml文件在resources子目录下，需要加上子目录 比如：conf/config.yaml
     * @param fileName
     * @return
     */
    public Map<String,Object> getYamlToMap(String fileName){
        LinkedHashMap<String, Object> yamls = new LinkedHashMap<>();
        Yaml yaml = new Yaml();
        try {
            InputStream in = YmlUtils.class.getClassLoader().getResourceAsStream(fileName);
            yamls = yaml.loadAs(in,LinkedHashMap.class);
        }catch (Exception e){
            //log.error("{} load failed !!!" , fileName);
        }
        return yamls;
    }

    /**
     * key格式：aaa.bbb.ccc
     * 通过properties的方式获取yaml中的属性值
     * @param key
     * @param yamlMap
     * @return
     */
    public Object getValue(String key, Map<String,Object> yamlMap){
        String[] keys = key.split("[.]");
        Object o = yamlMap.get(keys[0]);
        if(key.contains(".")){
            if(o instanceof Map){
                return getValue(key.substring(key.indexOf(".")+1),(Map<String,Object>)o);
            }else {
                return null;
            }
        }else {
            return o;
        }
    }

    /**
     * 使用递归的方式设置map中的值，仅适合单一属性
     * key的格式: "server.port"
     * server.port=111
     *
     **/
    public Map<String,Object> setValue(String key,Object value) {
        Map<String, Object> result = new LinkedHashMap<>();
        String[] keys = key.split("[.]");
        int i = keys.length - 1;
        result.put(keys[i], value);
        if (i > 0) {
            return setValue(key.substring(0, key.lastIndexOf(".")), result);
        }
        return result;
    }

    public Map<String,Object> setValue(Map<String,Object> map, String key, Object value){

        String[] keys = key.split("\\.");

        int len = keys.length;
        Map temp = map;
        for(int i = 0; i< len-1; i++){
            if(temp.containsKey(keys[i])){
                temp = (Map)temp.get(keys[i]);
            }else {
                return null;
            }
            if(i == len-2){
                temp.put(keys[i+1],value);
            }
        }
        for(int j = 0; j < len - 1; j++){
            if(j == len -1){
                map.put(keys[j],temp);
            }
        }
        return map;
    }


    /**
     * 修改yaml中属性的值
     * @param key key是properties的方式： aaa.bbb.ccc (key不存在不修改)
     * @param value 新的属性值 （新属性值和旧属性值一样，不修改）
     * @param yamlName
     * @return true 修改成功，false 修改失败。
     */
    public boolean updateYaml(String key, @Nullable Object value, String yamlName){

        Map<String, Object> yamlToMap = this.getYamlToMap(yamlName);
        if(null == yamlToMap) {
            return false;
        }
        Object oldVal = this.getValue(key, yamlToMap);

        //未找到key 不修改
        if(null == oldVal){
            //log.error("{} key is not found",key);
            return false;
        }
        //不是最小节点值，不修改
        if(oldVal instanceof Map){
            //log.error("input key is not last node {}",key);
            return false;
        }

        //新旧值一样 不修改
        if(value.equals(oldVal)){
            //log.info("newVal equals oldVal, newVal: {} , oldVal: {}",value,oldVal);
            return false;
        }

        Yaml yaml = new Yaml(OPTIONS);
        String path = this.getClass().getClassLoader().getResource(yamlName).getPath();
        try {
            Map<String, Object> resultMap = this.setValue(yamlToMap, key, value);
            if(resultMap != null){
                yaml.dump(this.setValue(yamlToMap,key,value),new FileWriter(path));
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            //log.error("yaml file update failed !");
            //log.error("msg : {} ",e.getMessage());
            //log.error("cause : {} ",e.getCause());
        }
        return false;
    }


    public static void main(String[] args) {

        YmlUtils configs = new YmlUtils();

        Map<String, Object> yamlToMap = configs.getYamlToMap("application.yml");

        System.out.println(yamlToMap);

        boolean b = configs.updateYaml("liang.aaa", "Intel Core i7", "application.yml");
        System.out.println(b);

        System.out.println(configs.getYamlToMap("application.yml"));
    }
}