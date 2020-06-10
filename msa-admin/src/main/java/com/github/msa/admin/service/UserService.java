package com.github.msa.admin.service;

import com.github.msa.admin.model.UserModel;
import com.github.msa.admin.model.UserRoleModel;
import com.github.msa.core.service.CurdService;

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
