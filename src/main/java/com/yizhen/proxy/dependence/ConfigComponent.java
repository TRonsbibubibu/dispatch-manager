package com.yizhen.proxy.dependence;

import com.yizhen.proxy.exception.BeansException;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by trons on 2017/6/1.
 */
public class ConfigComponent extends AbstractComponent {

    public void handle(Container ioc) throws BeansException {
        ResourceBundle resource = ResourceBundle.getBundle("application");
        Enumeration<String> keys = resource.getKeys();
        Map<String, String> cfg = new HashMap<String, String>();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            cfg.put(key, resource.getString(key));
        }

        ioc.set("config", cfg);
    }
}
