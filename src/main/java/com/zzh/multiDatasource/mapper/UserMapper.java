package com.zzh.multiDatasource.mapper;

import com.zzh.multiDatasource.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 宗子豪 on 2019-08-08
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAll();

}
