/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netbusy.coder.radomcoder;

import com.netbusy.util.datautil.DTC;

/**
 *
 * @author root
 */
public class CkUtil {
    public static int[] grCKData(){
        int [] re = new int[32];
        for(int i=0;i<32;i++) {
            re[i] = (int) (Math.random() * 2147483647);
        }return re;
    }
    public static int [] check(int ck,int[] ckdata){
        byte[] src = DTC.intToByteArray(ck);
        int[] re = new int[4];
        re[3] = ckdata[src[3]];
        re[2] = ckdata[src[2]];
        re[1] = ckdata[src[1]];
        re[0] = ckdata[src[0]];
        return re;
    }
}
