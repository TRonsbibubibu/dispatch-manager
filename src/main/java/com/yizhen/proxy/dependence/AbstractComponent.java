package com.yizhen.proxy.dependence;

/**
 * Created by trons on 2017/6/1.
 */
public abstract class AbstractComponent implements Component {

    private Component next = null;

    public boolean hasNext() {
        return this.next != null;
    }

    public Component next() {
        return this.next;
    }

    public Component setNext(Component next) {
        this.next = next;
        return next;
    }
}
