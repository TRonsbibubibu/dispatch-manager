package com.yizhen.proxy.dependence;

import com.yizhen.proxy.exception.BeansException;

/**
 * Created by trons on 2017/6/1.
 */
public interface Component {
    boolean hasNext();

    Component next();

    Component setNext(Component next);

    void handle(Container ioc) throws BeansException;
}
