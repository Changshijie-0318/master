package com.sxau.master.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 18:01
 * @Description:
 */
@Entity
@Table(name = "sys_notice", schema = "master", catalog = "")
public class SysNotice {
    private int id;
    private String title;
    private String content;
    private Integer type;
    private String targetUser;
    private Integer sourceUserId;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "target_user")
    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    @Basic
    @Column(name = "source_user_id")
    public Integer getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(Integer sourceUserId) {
        this.sourceUserId = sourceUserId;
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
        SysNotice notice = (SysNotice) o;
        return id == notice.id &&
                Objects.equals(title, notice.title) &&
                Objects.equals(content, notice.content) &&
                Objects.equals(type, notice.type) &&
                Objects.equals(targetUser, notice.targetUser) &&
                Objects.equals(sourceUserId, notice.sourceUserId) &&
                Objects.equals(createTime, notice.createTime) &&
                Objects.equals(updateTime, notice.updateTime) &&
                Objects.equals(state, notice.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, type, targetUser, sourceUserId, createTime, updateTime,  state);
    }
}
