package com.sxau.master.data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 13:18
 * @Description:
 */
@Entity
@Table(name = "sys_list", schema = "master", catalog = "")
public class SysList {
    private int id;
    private String listName;
    private String path;
    private Integer fId;
    private String icon;
    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
    @Column(name = "list_name")
    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "f_id")
    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysList sysList = (SysList) o;
        return id == sysList.id &&
                Objects.equals(listName, sysList.listName) &&
                Objects.equals(path, sysList.path) &&
                Objects.equals(fId, sysList.fId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listName, path, fId);
    }
}
