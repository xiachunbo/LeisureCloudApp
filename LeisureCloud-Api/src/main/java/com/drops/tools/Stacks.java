package com.drops.tools;

import java.util.LinkedList;

public class Stacks {
    public static LinkedList list = new LinkedList();

    public  synchronized void push(Object x) {
        synchronized (list) {
            System.out.println("进去push同步块"+list.size());
            System.out.println("push");
            list.addLast(x);
            notify();//这里是this。。当前对象的锁释放了
        }
    }

    public  synchronized Object pop() throws InterruptedException {
        synchronized (list) {
            System.out.println("进去pop同步块"+list.size());
            if (list.size() <= 0) {
                System.out.println("wait");
                wait();
            }
            //list.removeLast();
            System.out.println("当前集合长度"+list.size());
            return 0;
        }
    }
}
