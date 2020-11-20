package com.sxau.master.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 17:23
 * @Description:
 */
@Entity
@Table(name = "sys_shop_car", schema = "master", catalog = "")
public class SysShopCar {
    private int id;
    private int userIdBuy;
    private Integer userIdSell;
    private int proId;
    private Timestamp createTime;
    private Timestamp updateTime;
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
    @Column(name = "user_id_buy")
    public int getUserIdBuy() {
        return userIdBuy;
    }

    public void setUserIdBuy(int userIdBuy) {
        this.userIdBuy = userIdBuy;
    }

    @Basic
    @Column(name = "user_id_sell")
    public Integer getUserIdSell() {
        return userIdSell;
    }

    public void setUserIdSell(Integer userIdSell) {
        this.userIdSell = userIdSell;
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
        SysShopCar shopCar = (SysShopCar) o;
        return id == shopCar.id &&
                userIdBuy == shopCar.userIdBuy &&
                proId == shopCar.proId &&
                state == shopCar.state &&
                Objects.equals(userIdSell, shopCar.userIdSell) &&
                Objects.equals(createTime, shopCar.createTime) &&
                Objects.equals(updateTime, shopCar.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userIdBuy, userIdSell, proId, createTime, updateTime, state);
    }
}
