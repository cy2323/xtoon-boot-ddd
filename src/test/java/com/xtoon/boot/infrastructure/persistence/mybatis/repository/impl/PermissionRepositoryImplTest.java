package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.xtoon.boot.domain.model.Permission;
import com.xtoon.boot.domain.model.types.*;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.domain.shared.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 权限RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-02-17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class PermissionRepositoryImplTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    void query() {
        Map<String ,Object> param = new HashMap<>();
        param.put("permissionLevel", PermissionLevelEnum.TENANT.getValue());
        List<Permission> list = permissionRepository.queryList(param);
        logger.info("list="+list);
        assertThat(list).hasSizeGreaterThan(0);
    }

    @Test
    void find() {
        Permission permission = permissionRepository.find(new PermissionId("1362006127461568513"));
        logger.info("permission="+permission);
        assertThat(permission).isNotNull();
    }

    @Test
    void getUserPermissionCodes() {
    }

    @Test
    void store() {
        Permission parent = permissionRepository.find(new PermissionId("1362006127461568513"));
        Set<String> perms = new HashSet<>();
        perms.add("c");
        perms.add("v");
        PermissionCodes permissionCodes = new PermissionCodes(perms);
        Permission permission = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",permissionCodes,0,new MenuUrl("index"),
                parent, StatusEnum.ENABLE,null);
        permissionRepository.store(permission);
    }

    @Test
    void delete() {
        permissionRepository.remove(new PermissionId("1362006127461568513"));
    }
}