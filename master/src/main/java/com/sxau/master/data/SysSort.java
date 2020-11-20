package com.sxau.master.data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 16:56
 * @Description:
 */
@Entity
@Table(name = "sys_sort", schema = "master", catalog = "")
public class SysSort {
    private int id;
    private String sortName;
    private String faSort;

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
    @Column(name = "sort_name")
    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Basic
    @Column(name = "fa_sort")
    public String getFaSort() {
        return faSort;
    }

    public void setFaSort(String faSort) {
        this.faSort = faSort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysSort sysSort = (SysSort) o;
        return id == sysSort.id &&
                Objects.equals(sortName, sysSort.sortName) &&
                Objects.equals(faSort, sysSort.faSort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sortName, faSort);
    }
}
