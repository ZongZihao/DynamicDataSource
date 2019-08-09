package com.zzh.multiDatasource.controller;

import com.zzh.multiDatasource.datasource.DBIdentifier;
import com.zzh.multiDatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 宗子豪 on 2019-08-08
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/user")
    public Object user(@RequestParam(value = "projectCode", required = true) String projectCode){
        DBIdentifier.setProjectCode(projectCode);
        return userMapper.getAll();
    }

}
