package com.sxau.master.data;

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
@Table(name = "sys_file", schema = "master", catalog = "")
public class SysFile {
    private int id;
    private String fileName;
    private String fileFolder;
    private int fileType;
    private String description;
    private Integer creatorId;
    private Integer modifierId;
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
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_folder")
    public String getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    @Basic
    @Column(name = "file_type")
    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "creator_id")
    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "modifier_id")
    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
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
        SysFile sysFile = (SysFile) o;
        return id == sysFile.id &&
                fileType == sysFile.fileType &&
                Objects.equals(fileName, sysFile.fileName) &&
                Objects.equals(fileFolder, sysFile.fileFolder) &&
                Objects.equals(description, sysFile.description) &&
                Objects.equals(creatorId, sysFile.creatorId) &&
                Objects.equals(modifierId, sysFile.modifierId) &&
                Objects.equals(createTime, sysFile.createTime) &&
                Objects.equals(updateTime, sysFile.updateTime) &&
                Objects.equals(state, sysFile.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, fileFolder, fileType, description, creatorId, modifierId, createTime, updateTime, state);
    }
}
