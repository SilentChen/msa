package com.silent.msa.admin.dao;

import com.silent.msa.admin.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimary(UserModel record);

    List<UserModel> findPage(@Param(value = "name") String name);

    UserModel findByName(@Param(value = "name") String name);

    List<UserModel> findPageByName(@Param(value = "name") String name);

    List<UserModel> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);
}
