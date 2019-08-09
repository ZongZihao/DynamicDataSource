package com.zzh.multiDatasource.datasource;

import org.apache.tomcat.jdbc.pool.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 宗子豪 on 2019-08-08
 */
public class DDSHolder {

    private Map<String, DataSource> ddsMap = new HashMap<>();

    /**
     * 获取单例对象
     * @return
     */
    public static DDSHolder instance(){
        return DDSHolderBuilder.instance;
    }

    /**
     * 添加动态数据源
     * @param projectCode
     * @param dds
     */
    public synchronized void addDDS(String projectCode, DataSource dds){
        ddsMap.put(projectCode, dds);
    }

    /**
     * 获取数据源
     * @param projectCode
     * @return
     */
    public synchronized DataSource getDDS(String projectCode){
        if(ddsMap.containsKey(projectCode)){
            DataSource dds = ddsMap.get(projectCode);
            return dds;
        }
        return null;
    }



    private static class DDSHolderBuilder{
        private static DDSHolder instance = new DDSHolder();
    }

}
