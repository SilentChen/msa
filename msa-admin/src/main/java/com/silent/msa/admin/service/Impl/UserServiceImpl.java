package com.silent.msa.admin.service.Impl;

import com.silent.msa.admin.dao.UserDao;
import com.silent.msa.admin.dao.UserRoleDao;
import com.silent.msa.admin.model.UserModel;
import com.silent.msa.admin.model.UserRoleModel;
import com.silent.msa.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional
    @Override
    public int save(UserModel record) {
        Long id = null;
        if(null == record.getId() || 0 == record.getId()) {
            userDao.insertSelective(record);
            id = record.getId();
        }else{
            userDao.updateByPrimaryKeySelective(record);
        }
        if(null != id && 0 == id) {
            return 1;
        }
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();

        return perms;
    }

    @Override
    public List<UserRoleModel> findUserRoles(Long userId) {
        return userRoleDao.findUserRoles(userId);
    }
}
