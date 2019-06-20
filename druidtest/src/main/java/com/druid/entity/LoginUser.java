package com.druid.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Note: 登入用户(多账号管理)
 * @Author: Kidd.shi
 * @Create: 2018/12/10 13:14
 */
public class LoginUser implements Serializable{

    private Integer id;

    /**用户名 */
    private String userName;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 真实姓名
     */
    private String realName;
    /**密码 **/
    private String password;

    /**账号类型 0 主账号 1 子账号 **/
    private Integer type;

    /**创建时间 **/
    private Date createTime;

    /**更新时间 **/
    private Date updateTime;

    /**用户状态 **/
    private Integer status;

    /**外键id type(主账号) 为 0  enterprise_id 1(子账号) employee_id**/
    private Integer foreignId;

    /**企业id **/
    private Integer enterpriseId;

    private Integer createUserId;
    /** 最后一次登录时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getForeignId() {
        return foreignId;
    }

    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", foreignId=" + foreignId +
                ", enterpriseId=" + enterpriseId +
                ", createUserId=" + createUserId +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
