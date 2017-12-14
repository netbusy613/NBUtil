package com.netbusy.util.threadutil;

import com.netbusy.log.ByLog;

public class ThreadUtil {
    public static void notify(Object control){
        synchronized (control) {
            control.notify();
        }
    }
    public static void waits(Object control){
        waits(0,control);
    }
    public static void waits(int time,Object control) {
        if(control!=null) {
            if (time == 0) {
                synchronized (control) {
                    try {
                        ByLog.log(Thread.currentThread()+" waits...");
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (time > 0) {
                synchronized (control) {
                    try {
                        ByLog.log(Thread.currentThread()+" waits "+time+" ms");
                        control.wait(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            ByLog.err("waits error control = NULL");
        }
    }
    public static Thread CreatThread(Runnable runnable){
        Thread thread = new Thread(runnable);
        return thread;
    }
    public static void cNotify(Object control){
        synchronized (control){
            control.notify();
        }
    }
}