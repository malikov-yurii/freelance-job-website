package com.malikov.freelance.service;

import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.model.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;

import static com.malikov.freelance.FreelancerTestData.*;
import static com.malikov.freelance.SkillTestData.HTML;
import static com.malikov.freelance.SkillTestData.JAVASCRIPT;

public class FreelancerServiceTest extends AbstractServiceTest{

    @Autowired
    protected FreelancerService service;

    @Test
    public void save() throws Exception {
        Freelancer newFreelancer = new Freelancer("newFreelancerLogin", "newFreelancerPassword", "newFreelancerFirstName", "newFreelancerLastName",
                "newFreelancerEmail@gmail.com", new HashSet<Role>(Arrays.asList(Role.ROLE_USER, Role.ROLE_FREELANCER)), new HashSet<>(Arrays.asList(JAVASCRIPT, HTML)));
        Freelancer created = service.save(newFreelancer);
        FREELANCER_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        FREELANCER_1_YURII, FREELANCER_2_DENIS, FREELANCER_3_EGOR, FREELANCER_4_IVAN, created),
                service.getAll());
    }

    @Test
    public void update() throws Exception {
        Freelancer updated = new Freelancer(FREELANCER_3_EGOR);
        updated.setFirstName("Egor_Updated");
        service.update(updated);
        FREELANCER_MATCHER.assertEquals(updated, service.get(FREELANCER_3_EGOR.getId()));
    }

    @Test
    public void get() throws Exception {
        Freelancer client = service.get(FREELANCER_2_DENIS.getId());
        FREELANCER_MATCHER.assertEquals(FREELANCER_2_DENIS, client);
    }

    @Test
    public void getAll() throws Exception {
        FREELANCER_MATCHER.assertCollectionEquals(Arrays.asList(
                FREELANCER_1_YURII, FREELANCER_2_DENIS, FREELANCER_3_EGOR, FREELANCER_4_IVAN),
                service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(FREELANCER_3_EGOR.getId());
        FREELANCER_MATCHER.assertCollectionEquals(Arrays.asList(
                FREELANCER_1_YURII, FREELANCER_2_DENIS, FREELANCER_4_IVAN),
                service.getAll());
    }

    @Test
    public void getByLogin() throws Exception {
        Freelancer freelancer = service.getByLogin(FREELANCER_1_YURII.getLogin());
        FREELANCER_MATCHER.assertEquals(FREELANCER_1_YURII, freelancer);
    }
}
