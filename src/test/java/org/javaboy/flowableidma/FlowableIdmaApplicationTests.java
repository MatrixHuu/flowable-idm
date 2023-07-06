package org.javaboy.flowableidma;

import org.flowable.common.engine.api.management.TableMetaData;
import org.flowable.engine.IdentityService;
import org.flowable.engine.impl.IdentityServiceImpl;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmManagementService;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class FlowableIdmaApplicationTests {

    @Autowired
    IdentityService identityService;
    @Autowired
    IdmManagementService idmManagementService;
    private static final Logger logger = LoggerFactory.getLogger(FlowableIdmaApplicationTests.class);

    @Test
    void test14() {
        Map<String, String> properties = idmManagementService.getProperties();
        Set<String> key = properties.keySet();
        for (String s : key) {
            logger.info("key:{}; value:{}", s, properties.get(s));
        }

        String groupTableName = idmManagementService.getTableName(Group.class);
        logger.info("tableName:{}", groupTableName);

        TableMetaData tableMetaData = idmManagementService.getTableMetaData(groupTableName);
        logger.info("列名: {}", tableMetaData.getColumnNames());
        logger.info("列的类型: {}", tableMetaData.getColumnTypes());
        logger.info("表名: {}", tableMetaData.getTableName());
    }

    @Test
    void test13() {
        List<Group> list = identityService.createGroupQuery().groupMember("javaboy").list();
        for (Group group : list) {
            logger.info("id: {}, name: {}", group.getId(), group.getName());
        }
    }

    @Test
    void test12() {
        List<Group> list = identityService.createGroupQuery().groupName("CEO").list();
        for (Group group : list) {
            logger.info("id: {}, name: {}", group.getId(), group.getName());
        }
    }

    @Test
    void test11() {
        Group g = identityService.createGroupQuery().groupId("managers").singleResult();
        g.setName("CEO");
        identityService.saveGroup(g);
    }

    @Test
    void test10() {
        String userId = "javaboy";
        String groupId = "managers";
        identityService.createMembership(userId, groupId);
    }

    @Test
    void test09() {
        identityService.deleteGroup("leader");
    }

    @Test
    void test08() {
        GroupEntityImpl g = new GroupEntityImpl();
        g.setName("经理");
        g.setId("managers");
        g.setRevision(0);
        identityService.saveGroup(g);
    }

    @Test
    void test07() {
        List<User> list = identityService.createNativeUserQuery().sql("select * from ACT_ID_USER where EMAIL_ = #{email}").parameter("email", "javaboy@qq.com").list();
        for (User user : list) {
            logger.info("id: {}; displayName: {}", user.getId(), user.getDisplayName());
        }
    }

    @Test
    void test06() {
        List<User> list = identityService.createUserQuery().orderByUserId().desc().list();
        for (User user : list) {
            logger.info("id: {}; displayName: {}", user.getId(), user.getDisplayName());
        }
    }

    @Test
    void test05() {
        List<User> list = identityService.createUserQuery().userDisplayNameLike("%zhangsan%").list();
        for (User user : list) {
            logger.info("id: {}; displayName: {}", user.getId(), user.getDisplayName());
        }
    }

    @Test
    void test04() {
        identityService.deleteUser("javaboy");
    }

    @Test
    void test03() {
        User user = identityService.createUserQuery().userId("javaboy").singleResult();
        user.setEmail("33@33.com");
        user.setPassword("888");
        identityService.updateUserPassword(user);
    }

    @Test
    void test02() {
        User user = identityService.createUserQuery().userId("javaboy").singleResult();
        user.setEmail("221@22.com");
        identityService.saveUser(user);

    }

    @Test
    void test01() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("javaboy");
        user.setEmail("11@111.com");
        user.setRevision(3);
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
