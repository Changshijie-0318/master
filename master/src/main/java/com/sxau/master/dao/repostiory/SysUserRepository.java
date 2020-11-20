package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysUser;
import com.sxau.master.data.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 16:35
 * @Description:
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    SysUser findSysUserByIdAndState(int id, byte state);

    SysUser findSysUserByLoginNameAndState(String loginName, byte state);

    SysUser findSysUserByEmailAndState(String email,byte state);


}
