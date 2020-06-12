package com.github.msa.admin.service.Impl;

import com.github.msa.admin.dao.RoleDao;
import com.github.msa.admin.dao.UserDao;
import com.github.msa.admin.dao.UserRoleDao;
import com.github.msa.admin.model.MenuModel;
import com.github.msa.admin.model.RoleModel;
import com.github.msa.admin.model.UserModel;
import com.github.msa.admin.model.UserRoleModel;
import com.github.msa.admin.service.MenuService;
import com.github.msa.admin.service.UserService;
import com.github.msa.core.page.ColumnFilter;
import com.github.msa.core.page.MybatisPageHelper;
import com.github.msa.core.page.PageRequest;
import com.github.msa.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuService menuService;

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

        if(null != id) {
                for(UserRoleModel userRoleModel:record.getUserRoleModels()) {
                userRoleModel.setRoleId(id);
                }
        }else{
            userRoleDao.deleteByUserId(record.getId());
        }
        for(UserRoleModel userRoleModel : record.getUserRoleModels()) {
            userRoleDao.insertSelective(userRoleModel);
        }

        return 1;
    }

    @Override
    public int delete(UserModel record) {
        return userDao.deleteByPrimaryKey(record.getId());
    }

    @Override
    @Transactional
    public int delete(List<UserModel> records) {
        for(UserModel record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public UserModel findById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public UserModel findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult;
        String name = getColumnFilterValue(pageRequest, "name");
        String email = getColumnFilterValue(pageRequest, "email");
        if(null != name) {
            if(null != email) {
                pageResult = MybatisPageHelper.findPage(pageRequest, userDao, "findPageByNameAndEmail", name, email);
            }else{
                pageResult = MybatisPageHelper.findPage(pageRequest, userDao, "findPageByName", name);
            }
        }else {
            pageResult = MybatisPageHelper.findPage(pageRequest, userDao);
        }

        return pageResult;
    }

    @Override
    public List<UserRoleModel> findUserRoles(Long userId) {
        return userRoleDao.findUserRoles(userId);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<MenuModel> menuModels = menuService.findByUser(userName);
        for(MenuModel menuModel : menuModels) {
            if(null != menuModel.getPerms() && !"".equals(menuModel.getPerms())) {
                perms.add(menuModel.getPerms());
            }
        }
        return perms;
    }

    /**
     * get filter column value
     * @param pageRequest
     * @param filterName
     * @return
     */
    public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
        String value = null;
        ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
        if(null == columnFilter) {
            value = columnFilter.getValue();
        }

        return value;
    }

    /**
     * get userRoleList with given pageResult
     * @param pageResult
     * @return
     */
    public void findUserRoles(PageResult pageResult) {
        List<?> content = pageResult.getContent();
        for(Object object:content) {
            UserModel userModel = (UserModel) object;
            List<UserRoleModel> userRoles = findUserRoles(userModel.getId());
            userModel.setUserRoleModels(userRoles);
            userModel.setRoleNames(getRoleNames(userRoles));
        }
    }

    /**
     *
     * @param userRoleModels
     * @return
     */
    private String getRoleNames(List<UserRoleModel> userRoleModels) {
        StringBuilder sb = new StringBuilder();
        for(Iterator<UserRoleModel> iter = userRoleModels.iterator(); iter.hasNext();) {
            UserRoleModel userRoleModel = iter.next();
            RoleModel roleModel = roleDao.selectByPrimaryKey(userRoleModel.getRoleId());
            if(null == roleModel) {
                continue;
            }
            sb.append(roleModel.getRemark());
            if(iter.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
