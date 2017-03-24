package com.malikov.freelance.service;

import com.malikov.freelance.model.Project;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.malikov.freelance.ProjectTestData.*;

public class ProjectServiceTest extends AbstractServiceTest{

    @Autowired
    protected ProjectService service;

//    @Test
//    public void save() throws Exception {
//        Project newProject = new Project("newProjectName", Status.NEW, "newProjectDescription", new BigDecimal(1).setScale(2),
//                CLIENT_2_ROZA, FREELANCER_1_YURII, Collections.singletonList(FREELANCER_1_YURII), Collections.singletonList(JAVASCRIPT));
//        Project created = service.save(newProject);
//        PROJECT_MATCHER.assertCollectionEquals(
//                Arrays.asList(PROJECT_1_ADULT_SHOP, PROJECT_2_NEW_GOOGLE, PROJECT_3_SHOPPING_CARD, PROJECT_4_FILTER,
//                        PROJECT_5_CRM, PROJECT_6_VISIT_CARD, PROJECT_7_ONE_PAGE, PROJECT_8_SIMPLE_WEBSITE, PROJECT_9_SLIDER,
//                        created),
//                service.getAll());
//    }

    @Test
    public void update() throws Exception {
        Project updated = new Project(PROJECT_5_CRM);
        updated.setName("CRM_UPD");
        service.update(updated);
        PROJECT_MATCHER.assertEquals(updated, service.get(PROJECT_5_CRM.getId()));
    }

    @Test
    public void get() throws Exception {
        Project project = service.get(PROJECT_2_NEW_GOOGLE.getId());
        PROJECT_MATCHER.assertEquals(PROJECT_2_NEW_GOOGLE, project);
    }

    @Test
    public void getAll() throws Exception {
        PROJECT_MATCHER.assertCollectionEquals(
                Arrays.asList(PROJECT_1_ADULT_SHOP, PROJECT_2_NEW_GOOGLE, PROJECT_3_SHOPPING_CARD, PROJECT_4_FILTER,
                PROJECT_5_CRM, PROJECT_6_VISIT_CARD, PROJECT_7_ONE_PAGE, PROJECT_8_SIMPLE_WEBSITE, PROJECT_9_SLIDER),
                service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(PROJECT_2_NEW_GOOGLE.getId());
        PROJECT_MATCHER.assertCollectionEquals(
                Arrays.asList(PROJECT_1_ADULT_SHOP, PROJECT_3_SHOPPING_CARD, PROJECT_4_FILTER,
                        PROJECT_5_CRM, PROJECT_6_VISIT_CARD, PROJECT_7_ONE_PAGE, PROJECT_8_SIMPLE_WEBSITE, PROJECT_9_SLIDER),
                service.getAll());
    }
}
