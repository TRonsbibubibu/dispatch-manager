package com.yizhen.proxy.dependence;

import com.yizhen.proxy.exception.BeansException;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by trons on 2017/6/1.
 */
public class MybatisComponent extends AbstractComponent {

    @SuppressWarnings("unchecked")
    public void handle(Container ioc) throws BeansException {
        /**
         * app 环境
         */
        Map<String, String> config = (Map<String, String>) ioc.get("config");
        String env = config.get("env");

        /**
         * 事务管理器
         */
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        /**
         * 数据源
         */
        DataSource dataSource = (DataSource) ioc.get("dataSource");

        Environment environment = new Environment(env, transactionFactory, dataSource);
        Configuration cfg = new Configuration(environment);
        /**
         * mapper 文件目录
         */
        cfg.addMappers("com.yizhen.proxy.mapper");
        /**
         * 缓存的全局开关
         */
        cfg.setCacheEnabled(true);
        /**
         * useColumnLabel
         */
        cfg.setUseColumnLabel(true);
        /**
         * 允许 JDBC 支持自动生成主键
         */
        cfg.setUseGeneratedKeys(true);
        /**
         * 指定发现自动映射目标未知列
         */
        cfg.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.WARNING);
        /**
         * 开启自动驼峰命名规则（camel case）映射
         */
        cfg.setMapUnderscoreToCamelCase(true);
        /**
         * 默认返回 null
         */
        cfg.setReturnInstanceForEmptyRow(false);
        /**
         * 本地缓存机制
         */
        cfg.setLocalCacheScope(LocalCacheScope.SESSION);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(cfg);

        ioc.set("sqlSessionFactory", sqlSessionFactory);
    }
}
