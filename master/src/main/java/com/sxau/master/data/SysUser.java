package com.sxau.master.data;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 15:23
 * @Description:
 */
@Entity
@ToString
@Table(name = "sys_user", schema = "master", catalog = "")
public class SysUser {
    private int id;
    private String realName;
    private String loginName;
    private String password;
    private String email;
    private Byte state;
    private String phone;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String reason;
    private String address;
    private Integer imgUrl;
    private Byte role;
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "img_url")
    public Integer getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "role")
    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return id == sysUser.id &&
                Objects.equals(realName, sysUser.realName) &&
                Objects.equals(loginName, sysUser.loginName) &&
                Objects.equals(password, sysUser.password) &&
                Objects.equals(email, sysUser.email) &&
                Objects.equals(state, sysUser.state) &&
                Objects.equals(phone, sysUser.phone) &&
                Objects.equals(createTime, sysUser.createTime) &&
                Objects.equals(updateTime, sysUser.updateTime) &&
                Objects.equals(reason, sysUser.reason) &&
                Objects.equals(imgUrl, sysUser.imgUrl) &&
                Objects.equals(role, sysUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realName, loginName, password, email, state, phone, createTime, updateTime, reason, imgUrl, role);
    }
}
