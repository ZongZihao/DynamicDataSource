package com.zzh.multiDatasource.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 宗子豪 on 2019-08-09
 */
@Component
public class ProjectDBMgr {

    @Autowired
    private RedisTemplate redisTemplate;

//    public static ProjectDBMgr instance(){
//        return ProjectDBMgrBuilder.instance;
//    }

    public String getDBName(String projectCode){
        Map<String, String> dbNameMap = redisTemplate.opsForHash().entries("dbconf");
        if(dbNameMap.containsKey(projectCode)){
            return dbNameMap.get(projectCode);
        }
        return "";
    }

    public String getDBIP(String projectCode){
        Map<String, String> dbIPMap = redisTemplate.opsForHash().entries("dbip");
        if(dbIPMap.containsKey(projectCode)){
            return dbIPMap.get(projectCode);
        }
        return "";
    }



//    private static class ProjectDBMgrBuilder{
//        private static ProjectDBMgr instance = new ProjectDBMgr();
//    }

}
