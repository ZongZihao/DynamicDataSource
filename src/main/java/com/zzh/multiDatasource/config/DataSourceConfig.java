package com.zzh.multiDatasource.config;

import com.zzh.multiDatasource.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by 宗子豪 on 2019-08-08
 */

@Configuration
@MapperScan(basePackages = "com.zzh.multiDatasource.mapper", value = "sqlSessionFactory")
public class DataSourceConfig {

    /**
     * 根据配置参数创建数据源, 使用派生的子类
     * @return
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource getDateSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.type(DynamicDataSource.class);
        return builder.build();
    }


    /**
     * 创建会话工厂
     * @param dataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        try{
            return bean.getObject();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
