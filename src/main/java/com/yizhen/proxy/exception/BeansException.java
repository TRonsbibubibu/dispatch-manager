package com.yizhen.proxy.exception;

/**
 * Created by trons on 2017/6/1.
 */
public class BeansException extends Exception {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int hashCode() {
        return getMessage().hashCode();
    }
}
