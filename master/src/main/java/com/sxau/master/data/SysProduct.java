package com.sxau.master.data;

import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 15:23
 * @Description:
 */
@Entity
@ToString
@Table(name = "sys_product", schema = "master", catalog = "")
public class SysProduct {
    private int id;
    private int userId;
    private String proName;
    private String proDesc;
    private BigDecimal proPrice;
    private int proCount;
    private byte state;
    private Integer imgUrl;
    private Integer sortId;
    @Basic
    @Column(name = "sort_id")
    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    private Timestamp createTime;
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

    private Timestamp updateTime;

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
    @Column(name = "pro_name")
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Basic
    @Column(name = "pro_desc")
    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    @Basic
    @Column(name = "pro_price")
    public BigDecimal getProPrice() {
        return proPrice;
    }

    public void setProPrice(BigDecimal proPrice) {
        this.proPrice = proPrice;
    }

    @Basic
    @Column(name = "pro_count")
    public int getProCount() {
        return proCount;
    }

    public void setProCount(int proCount) {
        this.proCount = proCount;
    }

    @Basic
    @Column(name = "state")
    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "img_url")
    public Integer getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysProduct that = (SysProduct) o;
        return id == that.id &&
                userId == that.userId &&
                proCount == that.proCount &&
                state == that.state &&
                Objects.equals(proName, that.proName) &&
                Objects.equals(proDesc, that.proDesc) &&
                Objects.equals(proPrice, that.proPrice) &&
                Objects.equals(imgUrl, that.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, proName, proDesc, proPrice, proCount, state, imgUrl);
    }
}
