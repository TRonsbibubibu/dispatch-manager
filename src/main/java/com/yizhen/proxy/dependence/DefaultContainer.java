package com.yizhen.proxy.dependence;

import com.yizhen.proxy.exception.BeansException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by trons on 2017/6/1.
 */
public class DefaultContainer implements Container {

    private Map<String, Object> ioc;

    public DefaultContainer() {
        ioc = new ConcurrentHashMap<String, Object>();
    }

    public void set(String name, Object obj) throws BeansException {
        if (ioc.containsKey(name)) {
            throw new BeansException("repeat define bean: " + name);
        }
        ioc.put(name, obj);
    }

    public Object get(String name) throws BeansException {
        if (!ioc.containsKey(name)) {
            throw new BeansException("bean not found: " + name);
        }
        return ioc.get(name);
    }

    public boolean containsBean(String name) {
        return ioc.containsKey(name);
    }

    public Set<String> keys() {
        return ioc.keySet();
    }
}
