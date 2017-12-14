/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netbusy.coder.basecoder;

/**
 *
 * @author root
 */
public class CodeUtil {
    
    private static CodeUtilI cub = null;
    
    public static void updateCUB(CodeUtilI codeUtilI){
        CodeUtil.cub = codeUtilI;
    }
    
    private static CodeUtilI getCUB(){
        if(cub==null){
            System.out.println("use default CodeUtilImpl");
            cub = new CodeUtilImpl();
        }
        return cub;
    }

    public static byte[] getCode(int code) {
        return getCUB().getCode(code);
    }

    public static int grCode() {
        return getCUB().grCode();
    }
}
