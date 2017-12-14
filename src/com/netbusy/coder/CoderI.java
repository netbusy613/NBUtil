package com.netbusy.coder;

/**
 * Created by root on 1/13/17.
 */
public interface CoderI {
    Object grKey();
    byte[] encoding(Object locker, byte[] data);
    byte[] decoding(Object key, byte[] data);
}
