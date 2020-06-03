package com.silent.msa.admin.dao;

import com.silent.msa.admin.model.LogModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {
    int deleteByPrimaryKey(Long id);

    int insert(LogModel record);

    int insertSelective(LogModel record);

    LogModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogModel record);

    int updateByPrimaryKey(LogModel record);

    List<LogModel> findPage();

    List<LogModel> findPageUserName(@Param(value = "username") String username);
}
