package com.zzh.multiDatasource.datasource;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import lombok.extern.java.Log;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by 宗子豪 on 2019-08-08
 */
@Log
public class DynamicDataSource extends DataSource {

    @Autowired
    ProjectDBMgr projectDBMgr;

    @Override
    public Connection getConnection() {
        String projectCode = DBIdentifier.getProjectCode();

        //获取数据源
        DataSource dds = DDSHolder.instance().getDDS(projectCode);

        if (dds == null) {
            try {
                DataSource newDDS = initDDS(projectCode);
                DDSHolder.instance().addDDS(projectCode, newDDS);
            }catch(IllegalArgumentException | IllegalAccessException e){
                log.info("Init data source fail. projectCode:" + projectCode);
                return null;
            }
        }
        dds = DDSHolder.instance().getDDS(projectCode);
        try{
            return dds.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    private DataSource initDDS(String projectCode) throws IllegalAccessException {
        DataSource dds = new DataSource();
        //复制PoolConfiguration属性
        PoolProperties property = new PoolProperties();
        Field[] pfields = PoolProperties.class.getDeclaredFields();
        for (Field f : pfields) {
            f.setAccessible(true);
            Object value = f.get(this.getPoolProperties());

            try {
                f.set(property, value);
            } catch (Exception e) {
                //有一些static final的属性不能修改。忽略。
                log.info("Set value fail. attr name:" + f.getName());
                continue;
            }
        }
        dds.setPoolProperties(property);

        // 3、设置数据库名称和IP(一般来说，端口和用户名、密码都是统一固定的)
        String urlFormat = this.getUrl();
        String url = String.format(urlFormat, projectDBMgr.getDBIP(projectCode),
                projectDBMgr.getDBName(projectCode));
        dds.setUrl(url);

        return dds;
    }

}
