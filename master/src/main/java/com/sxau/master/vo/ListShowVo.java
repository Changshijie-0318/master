package com.sxau.master.vo;


import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 08:48
 * @Description:
 */
@Data
public class ListShowVo {
    private int id;
    private String listName;
    private String path;
    private Integer fId;
    private String icon;
    private List<ListShowVo> child;
}

