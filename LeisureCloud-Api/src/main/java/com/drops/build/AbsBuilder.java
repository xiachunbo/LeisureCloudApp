package com.drops.build;

/*
 * 产品立项相关操作
 */
abstract class AbsBuilder<T> {
    /**
     * 产品发布流程返回流程实例信息
     */
    abstract T build();
    /**
     * 产品发布流程启动
     */
    abstract T processOpertion1(String t);
    /**
     * 产品发布人员设置
     */
    abstract T processOpertion2(String t);
    /**
     * 产品发布流程提交
     */
    abstract T processOpertion3(String t);
    /**
     * 产品发布通过
     */
    abstract T processOpertion4(String t);
    /**
     * 产品发布驳回
     */
    abstract T processOpertion5(String t);
}
