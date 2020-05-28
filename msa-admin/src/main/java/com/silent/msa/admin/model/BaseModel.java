package com.silent.msa.admin.model;

import java.util.Date;

public class BaseModel {
    private Long id;
    private String createBu;
    private Date crreateTime;
    private String lastUpdateBy;
    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBu() {
        return createBu;
    }

    public void setCreateBu(String createBu) {
        this.createBu = createBu;
    }

    public Date getCrreateTime() {
        return crreateTime;
    }

    public void setCrreateTime(Date crreateTime) {
        this.crreateTime = crreateTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
