package com.github.msa.admin.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel extends BaseModel{
    private String name;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Byte status;
    private Long depId;
    private String roleName;
    private Byte delFlag;
    private List<UserRoleModel> userRoleModel = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getRoleNames() {
        return roleName;
    }

    public void setRoleNames(String roleName) {
        this.roleName = roleName;
    }

    public Byte getDelFlag() { return delFlag; }

    public void setDelFlag(Byte delFlag) { this.delFlag = delFlag; }

    public List<UserRoleModel> getUserRoleModels() {
        return userRoleModel;
    }

    public void setUserRoleModels(List<UserRoleModel> userRoleModel) {
        this.userRoleModel = userRoleModel;
    }
}
