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
public interface CodeUtilI {
    byte[] getCode(int code);
    int grCode();
    void setCode(String codestr);
}
