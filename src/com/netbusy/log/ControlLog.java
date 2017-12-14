package com.netbusy.log;

public class ControlLog implements LogI{
    public void println(Object o){
        System.out.println(o.toString());
    }
    public void print(Object o){
        System.out.print(o.toString());
    }

    @Override
    public void err(Object o) {
        System.err.println(o.toString());
    }
}
