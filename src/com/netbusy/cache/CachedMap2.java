package com.netbusy.cache;

import java.util.Date;
import java.util.HashMap;

public class CachedMap2 <K,V>{
    private int len = 999;
    private HashMap<K,V> h1;
    private HashMap<K,V> h2;
    private boolean use = false;
    private final Object control = new Date();

    public CachedMap2() {
        h1= new HashMap<K,V>();
        h2= new HashMap<K,V>();
    }

    public CachedMap2(int len) {
        this.len = len-1;
        h1= new HashMap<K,V>();
        h2= new HashMap<K,V>();
    }

    private HashMap<K,V> getUsedMap(){
        if(use){
            if(h2.size()==len){
                h1.clear();
                use = !use;
            }
            return h2;
        }else {
            if(h1.size()==len){
                h2.clear();
                use = !use;
            }
            return h1;
        }
    }
    public void put(K key,V value){
        synchronized (control){
            getUsedMap().put(key,value);
        }
    }

    public V get(K key){
        synchronized (control){
            V value = null;
            V value1 = h1.get(key);
            if (value1!=null){
                value =  value1;
                return value;
            }
            V value2 = h2.get(key);
            if(value2!=null){
                value = value2;
            }
            return value;
        }
    }

    public void remove(K key){
        synchronized (control){
            h1.remove(key);
            h2.remove(key);
        }
    }
}
