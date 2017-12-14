package com.netbusy.coder;


import com.netbusy.coder.basecoder.BaserCoder;

/**
 * Created by root on 1/13/17.
 */
public class CoderFactory {
    private static CoderI coderI;

    public static CoderI getCoderI() {
        if(coderI == null)
            init();
        return coderI;
    }

    public static void setCoderI(CoderI coderI) {
        CoderFactory.coderI = coderI;
    }
    private static void init(){
        coderI = new BaserCoder();
        System.out.println("use default coder!");
    }
}
