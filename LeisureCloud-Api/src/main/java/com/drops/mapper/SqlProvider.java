package com.drops.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SqlProvider {

    @SuppressWarnings(value={"unchecked","unused"})
    public String selectByParem(final String tableName,final String fields,final Map<String, Object> map){
        return  new SQL(){{
            SELECT(fields);
            FROM(tableName);
            for (String col : map.keySet()) {
                WHERE(""+ col +"=" + "\'"+map.get(col)+"\'");
            }
        }
        }.toString();
    }

    /**
     * id如果传入了值，会被insert使用；如果id为null，不会被insert的columns列出
     */
    @SuppressWarnings(value={"unchecked","unused"})
    public static String insert(final String tableName,final Map<String, Object> map) {
        return new SQL() {
            {
                INSERT_INTO(tableName);
                for (String col : map.keySet()) {
                    VALUES(col, "\'"+map.get(col).toString()+"\'");
                }
            }
        }.toString();
    }
    @SuppressWarnings(value={"unchecked","unused"})
    public static String updateByParem(final String tableName,final Map<String, Object> parem,final Map<String, Object> map) {
        return new SQL() {
            {
                UPDATE(tableName);
                for (String col : map.keySet()) {
                    SET(col + "=" + "\'"+ map.get(col)+"\'");
                }
                for (String col : parem.keySet()) {
                    WHERE(""+ col +"=" + "\'" + parem.get(col)+"\'");
                }
            }
        }.toString();
    }
    @SuppressWarnings(value={"unchecked","unused"})
    public static String deleteByParem(final String tableName,final Map<String, Object> map) {
        return new SQL() {
            {
                DELETE_FROM(tableName);
                for (String col : map.keySet()) {
                    WHERE(""+ col +"=" + "\'" + map.get(col)+"\'");
                }
            }
        }.toString();
    }
}

