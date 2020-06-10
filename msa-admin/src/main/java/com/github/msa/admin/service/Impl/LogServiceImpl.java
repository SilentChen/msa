package com.github.msa.admin.service.Impl;

import com.github.msa.admin.dao.LogDao;
import com.github.msa.admin.model.LogModel;
import com.github.msa.admin.service.LogService;
import com.github.msa.core.page.ColumnFilter;
import com.github.msa.core.page.MybatisPageHelper;
import com.github.msa.core.page.PageRequest;
import com.github.msa.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public int save(LogModel record) {
        if(null == record.getId() || 0 == record.getId()) {
            return logDao.insertSelective(record);
        }

        return logDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(LogModel record) {
        return logDao.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<LogModel> records) {
        for(LogModel record :records) {
            delete(record);
        }

        return 1;
    }

    @Override
    public LogModel findById(Long id) {
        return logDao.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("userName");
        if(null != columnFilter) {
            return MybatisPageHelper.findPage(pageRequest, logDao, "findPageByUserName", columnFilter.getValue());
        }

        return MybatisPageHelper.findPage(pageRequest, logDao);
    }
}
