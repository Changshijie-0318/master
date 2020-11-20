package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 17:40
 * @Description:
 */
public interface SysProductRepository extends JpaRepository<SysProduct,Integer> {

    SysProduct findSysProductById(int id);

}
