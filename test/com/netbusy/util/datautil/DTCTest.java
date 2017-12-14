package com.netbusy.util.datautil;

import com.netbusy.log.ByLog;

import static org.junit.Assert.*;

public class DTCTest {
    @org.junit.Test
    public void b2OCopy2() throws Exception {
        byte[] bytes = DTC.longToByte(1232131);
        Object[] objects = DTC.B2OCopy2(bytes,0,long.class);
        ByLog.log(objects[0]);
        ByLog.log(objects[1]);
    }

}