package com.netbusy.log;

public class ByLog {
    private static LogI logI;
    private static LogI getLog(){
        if(logI==null)
            return new ControlLog();
        return logI;
    }
    public static void log(Object o){
        getLog().println(o);
    }
    public static void err(Object o){
        getLog().err(o);
    }
}
