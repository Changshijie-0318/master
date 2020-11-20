package com.sxau.master.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 21:35
 * @Description:
 */
@Entity
@Table(name = "sys_user_notice", schema = "master", catalog = "")
public class SysUserNotice {
    private int id;
    private int userId;
    private String dealNotice;
    private String undealNotice;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte state;

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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "deal_notice")
    public String getDealNotice() {
        return dealNotice;
    }

    public void setDealNotice(String dealNotice) {
        this.dealNotice = dealNotice;
    }

    @Basic
    @Column(name = "undeal_notice")
    public String getUndealNotice() {
        return undealNotice;
    }

    public void setUndealNotice(String undealNotice) {
        this.undealNotice = undealNotice;
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
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserNotice that = (SysUserNotice) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(dealNotice, that.dealNotice) &&
                Objects.equals(undealNotice, that.undealNotice) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, dealNotice, undealNotice, createTime, updateTime, state);
    }
}
