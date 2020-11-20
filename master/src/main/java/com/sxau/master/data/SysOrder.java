package com.sxau.master.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 17:03
 * @Description:
 */
@Entity
@Table(name = "sys_order", schema = "master", catalog = "")
public class SysOrder {
    private int id;
    private int userIdSell;
    private int userIdBuy;
    private int proId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String address;
    private BigDecimal rate;
    private byte state;

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
    @Column(name = "rate")
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "user_id_sell")
    public int getUserIdSell() {
        return userIdSell;
    }

    public void setUserIdSell(int userIdSell) {
        this.userIdSell = userIdSell;
    }

    @Basic
    @Column(name = "user_id_buy")
    public int getUserIdBuy() {
        return userIdBuy;
    }

    public void setUserIdBuy(int userIdBuy) {
        this.userIdBuy = userIdBuy;
    }

    @Basic
    @Column(name = "pro_id")
    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "state")
    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysOrder sysOrder = (SysOrder) o;
        return id == sysOrder.id &&
                userIdSell == sysOrder.userIdSell &&
                userIdBuy == sysOrder.userIdBuy &&
                proId == sysOrder.proId &&
                state == sysOrder.state &&
                Objects.equals(createTime, sysOrder.createTime) &&
                Objects.equals(updateTime, sysOrder.updateTime) &&
                Objects.equals(address, sysOrder.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userIdSell, userIdBuy, proId, createTime, updateTime, address, state);
    }
}
