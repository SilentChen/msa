package com.silent.msa.admin.dao;

import com.silent.msa.admin.model.UserRoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleModel record);

    int insertSelective(UserRoleModel record);

    UserRoleModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleModel record);

    int updateByPrimaryKey(UserRoleModel record);

    List<UserRoleModel> findUserRoles(@Param(value = "userId") Long userId);

    int deleteByUserId(@Param(value = "userId") Long userId);
}
