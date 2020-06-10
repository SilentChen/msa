package com.github.msa.admin.service.Impl;

import com.github.msa.admin.constants.Constants;
import com.github.msa.admin.dao.MenuDao;
import com.github.msa.admin.model.MenuModel;
import com.github.msa.admin.service.MenuService;
import com.github.msa.core.page.MybatisPageHelper;
import com.github.msa.core.page.PageRequest;
import com.github.msa.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public int save(MenuModel record) {
        if(null == record.getId() || 0 == record.getId()) {
            return menuDao.insertSelective(record);
        }
        if(null == record.getParentId()) {
            record.setParentId(0L);
        }

        return menuDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(MenuModel record) {
        return menuDao.deleteByPrimaryKey(record.getId());
    }

    @Transactional
    @Override
    public int delete(List<MenuModel> records) {
        for(MenuModel menuModel : records) {
            delete(menuModel);
        }

        return 1;
    }

    @Override
    public MenuModel findById(Long id) {
        return menuDao.selectBuPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, menuDao);
    }

    @Override
    public List<MenuModel> findByUser(String userName) {
        if(null == userName || "".equals(userName) || Constants.ADMIN.equalsIgnoreCase(userName)) {
            return menuDao.findAll();
        }

        return menuDao.findByUserName(userName);
    }

    @Override
    public List<MenuModel> findTree(String userName, int menuType) {
        List<MenuModel> menuModels = new ArrayList<>();
        List<MenuModel> menus = findByUser(userName);
        for(MenuModel menu : menus) {
            if(null == menu.getParentId() || 0 == menu.getParentId()) {
                menu.setLevel(0);
                if(!exists(menuModels, menu)) {
                    menuModels.add(menu);
                }
            }
        }

        menuModels.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(menuModels, menus, menuType);

        return menuModels;
    }

    private void findChildren(List<MenuModel> menuModels, List<MenuModel> menus, int menuType) {
        for(MenuModel menuModel : menuModels) {
            List<MenuModel> children = new ArrayList<>();
            for(MenuModel menu : menus) {
                if(menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮， 且菜单类型是按钮的， 直接跳过
                    continue;
                }
                if(null != menuModel.getId() && menuModel.getId().equals(menu.getParentId())) {
                    menu.setParentName(menuModel.getName());
                    menu.setLevel(menuModel.getLevel() + 1);
                    if(!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            menuModel.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }

    }

    /**
     * check argument 2 element exist in argument 1 list or not.
     * @param menuModels
     * @param menuModel
     * @return
     */
    private boolean exists(List<MenuModel> menuModels, MenuModel menuModel) {
        boolean exist = false;
        for(MenuModel menu : menuModels) {
            if(menu.getId().equals(menuModel.getId())) {
                exist = true;
                break;
            }
        }

        return exist;
    }
}
