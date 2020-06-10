package com.github.msa.admin.dao;

import com.github.msa.admin.model.RoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(RoleModel record);

    int insertSelective(RoleModel record);

    RoleModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleModel record);

    int updateByPrimaryKey(RoleModel record);

    List<RoleModel> findPage();

    List<RoleModel> findAll();

    List<RoleModel> findPageByName(@Param(value="name") String name);

    List<RoleModel> findByName(@Param(value="name") String name);

}
