package com.zzh.multiDatasource.datasource;

/**
 * Created by 宗子豪 on 2019-08-08
 */
public class DBIdentifier {

    private static ThreadLocal<String> projectCode = new ThreadLocal<String>();

    public static String getProjectCode(){
        return projectCode.get();
    }

    public static void setProjectCode(String code){
        projectCode.set(code);
    }

}
