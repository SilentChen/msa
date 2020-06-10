package com.github.msa.admin.model;

import java.util.List;

public class DeptModel {
    private String name;

    private Long parentId;

    private Integer orderNum;

    private Byte delFlag;

    private List<DeptModel> children;

    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public List<DeptModel> getChildren() {
        return children;
    }

    public void setChildren(List<DeptModel> children) {
        this.children = children;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
