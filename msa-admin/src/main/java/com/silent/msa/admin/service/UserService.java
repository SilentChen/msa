package com.silent.msa.admin.service;

import com.silent.msa.admin.model.UserModel;
import com.silent.msa.admin.model.UserRoleModel;
import com.silent.msa.core.service.CurdService;

import java.util.List;
import java.util.Set;

/**
 * @author Silent
 * @date Oct 29, 2019
 */
public interface UserService extends CurdService<UserModel> {
    UserModel findByName(String username);

    /**
     * find user's permission set via username
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    /**
     * find user role set via userId
     * @param userId
     * @return
     */
    List<UserRoleModel> findUserRoles(Long userId);
}
