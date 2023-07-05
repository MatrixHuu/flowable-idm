package org.javaboy.flowableidma;

import org.flowable.engine.IdentityService;
import org.flowable.engine.impl.IdentityServiceImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowableIdmaApplicationTests {

    @Autowired
    IdentityService identityService;

    @Test
    void test01(){
        UserEntityImpl user = new UserEntityImpl();
        user.setId("javaboy");
        user.setPassword("666");
        user.setEmail("666@666.com");
        identityService.saveUser(user);
    }
    @Test
    void contextLoads() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("javaboy");
        user.setDisplayName("江南一点雨");
        user.setPassword("123");
        user.setFirstName("javaboy");
        user.setLastName("javaboy");
        user.setEmail("javaboy@qq.com");
        user.setRevision(0);
        identityService.saveUser(user);
    }

}
