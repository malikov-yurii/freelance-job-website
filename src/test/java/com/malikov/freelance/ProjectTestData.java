package com.malikov.freelance;

import com.malikov.freelance.matcher.ModelMatcher;
import com.malikov.freelance.model.Project;
import com.malikov.freelance.model.ProjectStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static com.malikov.freelance.ClientTestData.*;
import static com.malikov.freelance.CommentTestData.*;
import static com.malikov.freelance.FreelancerTestData.FREELANCER_1_YURII;
import static com.malikov.freelance.FreelancerTestData.FREELANCER_2_DENIS;
import static com.malikov.freelance.SkillTestData.*;


public class ProjectTestData {
    public static final Project PROJECT_1_ADULT_SHOP = new Project(1, "Online shop of adult toys", ProjectStatus.NEW,
            "I need online shop to be developed very fast (maybe in 1-2 days not more than that)", new BigDecimal(25).setScale(2), CLIENT_1_SIMA, null, Collections.emptyList(),
            Arrays.asList(HTML, CSS, JAVASCRIPT), Collections.emptyList());
    public static final Project PROJECT_2_NEW_GOOGLE = new Project(2, "New search system", ProjectStatus.LOOKING_FOR_FREELANCER,
            "I have idea. You should hack Google or Yandex. And to user its source code for our new search system. We'll spit benefits 50/50", new BigDecimal(3000).setScale(2), CLIENT_2_ROZA, null, Collections.emptyList(),
            Collections.singletonList(JAVA), new ArrayList<>(Arrays.asList(PROJECT2_YURII_COMMENT_1, PROJECT2_ROZA_COMMENT_2)));
    public static final Project PROJECT_3_SHOPPING_CARD = new Project(3, "Shopping card", ProjectStatus.FREELANCER_ASSIGNED,
            "Add shopping card to my online shop", new BigDecimal(50).setScale(2), CLIENT_3_ISAAK, FREELANCER_2_DENIS, Collections.emptyList(),
            Arrays.asList(HTML, CSS), Collections.emptyList());
    public static final Project PROJECT_4_FILTER = new Project(4, "Filter for products", ProjectStatus.IN_PROGRESS,
            "Add convenient feature for my CRM to filter products in table", new BigDecimal(250).setScale(2), CLIENT_3_ISAAK, FREELANCER_1_YURII, Collections.emptyList(),
            Collections.singletonList(JAVA), Collections.emptyList());
    public static final Project PROJECT_5_CRM = new Project(5, "CRM", ProjectStatus.FINISHED,
            "Built CRM for to manage my business (store information about products and customers)", new BigDecimal(3555).setScale(2), CLIENT_3_ISAAK, FREELANCER_1_YURII, Collections.emptyList(),
            Arrays.asList(HTML, CSS, JAVASCRIPT, JAVA, SQL, HIBERNATE, SPRING, MAVEN), Collections.emptyList());
    public static final Project PROJECT_6_VISIT_CARD = new Project(6, "Visit card website", ProjectStatus.LOOKING_FOR_FREELANCER,
            "Need personal website for my dog Buddy", new BigDecimal(75).setScale(2), CLIENT_3_ISAAK, null, Arrays.asList(FREELANCER_1_YURII, FREELANCER_2_DENIS),
            Arrays.asList(HTML, CSS), new ArrayList<>(Arrays.asList(PROJECT6_YURII_COMMENT_3, PROJECT6_DENIS_COMMENT_4)));
    public static final Project PROJECT_7_ONE_PAGE = new Project(7, "One page website", ProjectStatus.LOOKING_FOR_FREELANCER,
            "Need single page website to sell my dog's hilarious video tape", new BigDecimal(125).setScale(2), CLIENT_3_ISAAK, null, Arrays.asList(FREELANCER_1_YURII, FREELANCER_2_DENIS),
            Arrays.asList(HTML, CSS), new ArrayList<>(Arrays.asList(PROJECT7_DENIS_COMMENT_5, PROJECT7_YURII_COMMENT_6)));
    public static final Project PROJECT_8_SIMPLE_WEBSITE = new Project(8, "Simple website for photos", ProjectStatus.FINISHED,
            "Construct simple website where i would be able to show photos of my projects to peope.", new BigDecimal(135).setScale(2), CLIENT_3_ISAAK, FREELANCER_1_YURII, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    public static final Project PROJECT_9_SLIDER = new Project(9, "Photo-slider", ProjectStatus.FINISHED,
            "Photo-slider module for simple website", new BigDecimal(55).setScale(2), CLIENT_3_ISAAK, FREELANCER_1_YURII, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

    public static final ModelMatcher<Project> PROJECT_MATCHER = ModelMatcher.of(Project.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getStatus(), actual.getStatus())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getPayment(), actual.getPayment())
                            && Objects.equals(expected.getClient(), actual.getClient())
                            && Objects.equals(expected.getFreelancer(), actual.getFreelancer())
                            && Objects.equals(expected.getAppliedFreelancers(), actual.getAppliedFreelancers())
                            && Objects.equals(expected.getRequiredSkills(), actual.getRequiredSkills())
                            && Objects.equals(expected.getComments(), actual.getComments())
                    )
    );
}

