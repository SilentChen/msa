package com.silent.msa.admin.service;

import com.silent.msa.admin.model.MenuModel;
import com.silent.msa.core.service.CurdService;

import java.util.List;

/**
 * menu managerment
 * @author Silent
 * @date Oct 29, 2019
 */
public interface MenuService extends CurdService<MenuModel> {
    /**
     * menu tree list, userId and userName is null, return all.
     * @param userName
     * @param menuType
     * @return
     */
    List<MenuModel> findTree(String userName, int menuType);

    /**
     * get menu with given userName
     * @param userName
     * @return
     */
    List<MenuModel> findByUser(String userName);
}
