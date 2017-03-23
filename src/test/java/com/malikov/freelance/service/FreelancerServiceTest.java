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
                "newFreelancerEmail", new HashSet<Role>(Arrays.asList(Role.USER, Role.FREELANCER)), new HashSet<>(Arrays.asList(JAVASCRIPT, HTML)));
        Freelancer created = service.save(newFreelancer);
        FREELANCER_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        YURII_FREELANCER_1, DENIS_FREELANCER_2, EGOR_FREELANCER_3, IVAN_FREELANCER_4, created),
                service.getAll());
    }

    @Test
    public void update() throws Exception {
        Freelancer updated = new Freelancer(EGOR_FREELANCER_3);
        updated.setFirstName("Egor_Updated");
        service.update(updated);
        FREELANCER_MATCHER.assertEquals(updated, service.get(EGOR_FREELANCER_3.getId()));
    }

    @Test
    public void get() throws Exception {
        Freelancer client = service.get(DENIS_FREELANCER_2.getId());
        FREELANCER_MATCHER.assertEquals(DENIS_FREELANCER_2, client);
    }

    @Test
    public void getAll() throws Exception {
        FREELANCER_MATCHER.assertCollectionEquals(Arrays.asList(
                YURII_FREELANCER_1, DENIS_FREELANCER_2, EGOR_FREELANCER_3, IVAN_FREELANCER_4),
                service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(EGOR_FREELANCER_3.getId());
        FREELANCER_MATCHER.assertCollectionEquals(Arrays.asList(
                YURII_FREELANCER_1, DENIS_FREELANCER_2, IVAN_FREELANCER_4),
                service.getAll());
    }
}
