package com.netbusy.util.datautil;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.nio.charset.Charset;

/**
 *
 * @author root
 */
public class DTC {

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF
                | (b[2] & 0xFF) << 8
                | (b[1] & 0xFF) << 16
                | (b[0] & 0xFF) << 24;
    }
    public static boolean byteToBoolean(byte a) {
        return (a == 0x00) ? false : true;
    }
    public static byte booleanToByte(boolean b) {
        return (byte)( b ? 0x01 : 0x00);
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
            (byte) ((a >> 24) & 0xFF),
            (byte) ((a >> 16) & 0xFF),
            (byte) ((a >> 8) & 0xFF),
            (byte) (a & 0xFF)
        };
    }
    //long类型转成byte数组 
  public static byte[] longToByte(long number) { 
        long temp = number; 
        byte[] b = new byte[8]; 
        for (int i = 0; i < b.length; i++) { 
            b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位 
        } 
        return b; 
    } 
    
    //byte数组转成long 
    public static long byteToLong(byte[] b) { 
        long s = 0; 
        long s0 = b[0] & 0xff;// 最低位 
        long s1 = b[1] & 0xff; 
        long s2 = b[2] & 0xff; 
        long s3 = b[3] & 0xff; 
        long s4 = b[4] & 0xff;// 最低位 
        long s5 = b[5] & 0xff; 
        long s6 = b[6] & 0xff; 
        long s7 = b[7] & 0xff; 
 
        // s0不变 
        s1 <<= 8; 
        s2 <<= 16; 
        s3 <<= 24; 
        s4 <<= 8 * 4; 
        s5 <<= 8 * 5; 
        s6 <<= 8 * 6; 
        s7 <<= 8 * 7; 
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7; 
        return s; 
    } 
    
    public static byte [] stringToBytes(String str){
        return str.getBytes(Charset.forName("utf-8"));
    }
    public static String bytesToString(byte bs[]){
        return new String(bs, Charset.forName("utf-8"));
    }

    public static byte[] objectToBytes (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object bytesToObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
//            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
        }
        return obj;
    }

    public static int O2BCopy(byte[] buf,int destPos,Object object) {
        byte[] bytes = null;
        if(object instanceof Integer) {
            bytes = intToByteArray((Integer) object);
        }
        else if(object instanceof Long) {
            bytes = longToByte((Long) object);
        }
        else if(object instanceof String) {
            String str = (String) object;
            bytes = str.getBytes(Charset.forName("UTF-8"));
        }else if(object instanceof Boolean){
            boolean b = (Boolean) object;
            bytes = new byte[1];
            bytes[0] = booleanToByte(b);
        }
        else{
            bytes = (byte[]) object;
        }
        System.arraycopy(bytes, 0,buf , destPos, bytes.length);
        return destPos+bytes.length;
    }

    public static Object B2OCopy(byte[] buf,int srcPos,int len,Class type){
        Object o = null;
        byte[] bytes= new byte[len];
        System.arraycopy(buf, srcPos,bytes , 0, len);
        if(type.equals(int.class)){
            return DTC.byteArrayToInt(bytes);
        }
        if(type.equals(long.class)){
            return DTC.byteToLong(bytes);
        }
        if(type.equals(byte[].class)){
            return bytes;
        }
        if(type.equals(boolean.class)){
            return DTC.byteToBoolean(bytes[0]);
        }
        if(type.equals(String.class)){
            return new String(bytes,Charset.forName("UTF-8"));
        }
        return o;
    }
    public static Object[] B2OCopy2(byte[] buf,int srcPos,Class type){
        Object[] o = new Object[2];
        int len = 0;
        byte[] bytes=null;
        if(type.equals(int.class)){
            len = 4;
            bytes= new byte[len];
            System.arraycopy(buf, srcPos,bytes , 0, len);
            o[0] = srcPos+len;
            o[1] = DTC.byteArrayToInt(bytes);
            return o;
        }
        if(type.equals(long.class)){
            len = 8;
            bytes= new byte[len];
            System.arraycopy(buf, srcPos,bytes , 0, len);
            o[0] = srcPos+len;
            o[1] = DTC.byteToLong(bytes);
            return o;
        }
        if(type.equals(byte[].class)){
            len = buf.length-srcPos;
            bytes= new byte[len];
            System.arraycopy(buf,srcPos,bytes,0,len);
            o[0] = srcPos+len;
            o[1] = bytes;
            return o;
        }
        if(type.equals(String.class)){
            len = 36;
            bytes= new byte[36];
            System.arraycopy(buf,srcPos,bytes,0,len);
            o[0] = srcPos+len;
            o[1] = new String(bytes);
            return o;
        }
        return o;
    }
}
