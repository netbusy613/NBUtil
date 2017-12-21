/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netbusy.coder.basecoder;

import com.netbusy.util.datautil.DTC;

/**
 *
 * @author root
 */
public class CodeUtilImpl implements CodeUtilI {

    private  byte[] codes = DTC.stringToBytes("kv(d98<!");

    @Override
    public byte[] getCode(int code) {
        byte [] c1 = DTC.intToByteArray(code);
        int  [] cs = new int[4];
        cs[0] = c1[0];
        cs[1] = c1[1];
        cs[2] = c1[2];
        cs[3] = c1[3];
        return new byte[]{codes[cs[0]],codes[cs[1]],codes[cs[2]],codes[cs[3]]};
    }
    @Override
    public int grCode() {
        int len = codes.length;
        byte [] code = new byte[4];
        for (int i=0;i<4;i++) {
            code[i] = (byte) (Math.random() * len);
        }
        return DTC.byteArrayToInt(code);
    }

    @Override
    public void setCode(String codestr) {
        codes = DTC.stringToBytes(codestr);
    }
}
