/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netbusy.coder.basecoder;

import com.netbusy.coder.CoderI;
import com.netbusy.util.datautil.DTC;

/**
 *
 * @author root
 */
public class BaserCoder implements CoderI {

    private static byte[] coding(byte[] code, byte[] bs) {
        if (code == null) {
            code = new byte[1];
            code[0] = 0;
        }
        int clen = code.length;
        int len = bs.length;
        byte re[] = new byte[len];
        for (int i = 0; i < len; i = i + clen) {
            for (int j = 0; j < clen; j++) {
                if ((i + j) >= len) {
                    break;
                }
                re[i + j] = (byte) (bs[i + j] ^ code[j]);
            }
        }
        return re;
    }

    private static byte[] coding(int[] code, byte[] bs) {
        if (code == null) {
            code = new int[1];
            code[0] = 0;
        }
        byte[] cs = new byte[code.length * 4];
        for (int i = 0; i < code.length; i++) {
            byte[] temp = DTC.intToByteArray(code[i]);
            System.arraycopy(temp, 0, cs, i * 4, 4);
        }
        byte[] re = coding(cs, bs);
        return re;
    }

    @Override
    public Object grKey() {
        return CodeUtil.grCode();
    }

    @Override
    public byte[] encoding(Object locker, byte[] data) {
        int code = (Integer) locker;
        byte[] codes = CodeUtil.getCode(code);
        return coding(codes,data);
    }

    @Override
    public byte[] decoding(Object key, byte[] data) {
        int code = (Integer) key;
        byte[] codes = CodeUtil.getCode(code);
        return coding(codes,data);
    }

}
