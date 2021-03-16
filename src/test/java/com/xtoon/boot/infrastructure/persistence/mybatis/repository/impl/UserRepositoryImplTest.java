package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.xtoon.boot.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户Repository测试类
 *
 * @author haoxin
 * @date 2021-02-13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryImplTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserPermissions() {
    }

}