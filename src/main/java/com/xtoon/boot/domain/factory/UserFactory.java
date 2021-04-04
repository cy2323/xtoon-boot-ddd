package com.xtoon.boot.domain.factory;

import com.xtoon.boot.domain.model.types.*;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户工厂
 *
 * @author haoxin
 * @date 2021-02-24
 **/
@Component
public class UserFactory {

    @Autowired
    private UserRepository userRepository;

    public User createUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList,TenantId currentTenantId) {
        List<User> users = userRepository.find(mobile);
        Account account;
        if(users != null && !users.isEmpty()) {
            for(User user : users) {
                if(user.getTenantId().sameValueAs(currentTenantId)) {
                    throw new RuntimeException("租户内账号已存在");
                }
            }
            account = users.get(0).getAccount();
        } else {
            account = new Account(mobile, email, password);
        }
        if(roleIdList == null || roleIdList.isEmpty()) {
            throw new RuntimeException("角色未分配");
        }
        return new User(userName,account,roleIdList);
    }

}
