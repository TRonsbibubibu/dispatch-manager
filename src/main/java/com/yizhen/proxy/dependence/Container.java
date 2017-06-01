package com.yizhen.proxy.dependence;

import com.yizhen.proxy.exception.BeansException;

import java.util.Set;

/**
 * Created by trons on 2017/6/1.
 */
public interface Container {
    Object get(String name) throws BeansException;

    void set(String name, Object obj) throws BeansException;

    boolean containsBean(String name);

    Set<String> keys();
}
