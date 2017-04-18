package com.malikov.freelance.service;

import com.malikov.freelance.model.Role;
import com.malikov.freelance.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static com.malikov.freelance.ClientTestData.*;
import static com.malikov.freelance.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void testSave() throws Exception {
        User newUser = new User("newUserlogin", "newUserPassword", "newUserFirstName", "newUserLastName", "newUserEmail@gmail.com", new HashSet<Role>(Arrays.asList(Role.ROLE_USER)));
        User created = service.save(newUser);
        USER_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        YURII_FREELANCER_1, DENIS_FREELANCER_2, EGOR_FREELANCER_3, IVAN_FREELANCER_4,
                        VAHTANG_ADMIN_1, GOGA_ADMIN_2, CLIENT_1_SIMA,
                        CLIENT_2_ROZA, CLIENT_3_ISAAK, created),
                new ArrayList<>(service.getAll()));
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(IVAN_FREELANCER_4);
        updated.setFirstName("Ivan_Updated");
        service.update(updated);
        USER_MATCHER.assertEquals(updated, service.get(IVAN_FREELANCER_4.getId()));
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(YURII_FREELANCER_1.getId());
        USER_MATCHER.assertEquals(YURII_FREELANCER_1, user);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User User = service.getByLogin(DENIS_FREELANCER_2.getLogin());
        USER_MATCHER.assertEquals(DENIS_FREELANCER_2, User);
    }

    @Test
    public void testGetAll() throws Exception {
        USER_MATCHER.assertCollectionEquals(Arrays.asList(
                YURII_FREELANCER_1, DENIS_FREELANCER_2, EGOR_FREELANCER_3,  IVAN_FREELANCER_4,
                VAHTANG_ADMIN_1, GOGA_ADMIN_2, CLIENT_1_SIMA,
                CLIENT_2_ROZA, CLIENT_3_ISAAK), new ArrayList<>(service.getAll()));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(EGOR_FREELANCER_3.getId());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(
                YURII_FREELANCER_1, DENIS_FREELANCER_2,  IVAN_FREELANCER_4,
                VAHTANG_ADMIN_1, GOGA_ADMIN_2, CLIENT_1_SIMA,
                CLIENT_2_ROZA, CLIENT_3_ISAAK), new ArrayList<>(service.getAll()));
    }
}