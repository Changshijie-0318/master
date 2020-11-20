package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-17 16:48
 * @Description:
 */
@Repository
public interface SysUserTokenRepository extends JpaRepository<SysUserToken,Integer> {
    SysUserToken findSysUserTokenByUserIdAndStatus(int userId,byte status);
}
