package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.PermissionId;

/**
 * 权限Service
 *
 * @author haoxin
 * @date 2021-02-17
 **/
public interface PermissionService {

    /**
     * 保存
     *
     * @param permission
     * @param parentPermissionId
     */
    void saveOrUpdate(Permission permission, PermissionId parentPermissionId);

    /**
     * 删除
     *
     * @param permissionId
     */
    void delete(PermissionId permissionId);

    /**
     * 禁用
     *
     * @param permissionId
     */
    void disable(PermissionId permissionId);
}
