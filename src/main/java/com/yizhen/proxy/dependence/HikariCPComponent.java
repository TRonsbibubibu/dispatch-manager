package com.yizhen.proxy.dependence;

import com.yizhen.proxy.exception.BeansException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Created by trons on 2017/6/1.
 */
public class HikariCPComponent extends AbstractComponent {


    public void handle(Container ioc) throws BeansException {
        HikariConfig config = new HikariConfig("/hikari.properties");

        DataSource ds = new HikariDataSource(config);
        ioc.set("dataSource", ds);
    }
}
