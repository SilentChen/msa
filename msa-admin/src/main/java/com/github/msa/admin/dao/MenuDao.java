package com.github.msa.admin.dao;

import com.github.msa.admin.model.MenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    int deleteByPrimaryKey(Long id);

    int insert(MenuModel record);

    int insertSelective(MenuModel record);

    MenuModel selectBuPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuModel record);

    int updateByPrimaryKey(MenuModel record);

    List<MenuModel> findPage();

    List<MenuModel> findPageByName(@Param(value="name") String name);

    List<MenuModel> findAll();

    List<MenuModel> findByUserName(@Param(value="userName") String userName);

    List<MenuModel> findRoleMenus(@Param(value="roleId") Long roleId);
}
