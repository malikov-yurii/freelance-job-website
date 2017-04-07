package com.malikov.freelance.util;

import com.malikov.freelance.model.*;
import com.malikov.freelance.to.ProjectTo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class ProjectUtil {

    public static ProjectTo asTo(Project project, ApplicationStatus applicationStatus, User authorizedUser) {
        return new ProjectTo(project.getId(), project.getClient().getId(), project.getName(),
                project.getDescription(), project.getPayment(), project.getClientLastName(),
                project.getStatus(), SkillUtil.skillCollectionToString(project.getRequiredSkills()), applicationStatus,
                authorizedUser.getRoles().contains(Role.ROLE_ADMIN) ||
                        (authorizedUser.getRoles().contains(Role.ROLE_CLIENT)
                                        && Objects.equals(authorizedUser.getId(), project.getClient().getId()))
                        ? project.getAppliedFreelancers()
                        : Collections.emptyList()
                , authorizedUser.getRoles().contains(Role.ROLE_ADMIN)
                    ? project.getComments()
                    : project.getComments().stream().filter(comment -> !comment.getBlocked()).collect(Collectors.toList())
        , project.getBlocked());
    }

    public static Project newFromTo(ProjectTo projectTo, Client client, Set<Skill> skills) {
        return new Project(
                null
                , projectTo.getName() == null ? "No name provided" : projectTo.getName()
                , ProjectStatus.LOOKING_FOR_FREELANCER
                , projectTo.getDescription() == null ? "No description provided" : projectTo.getDescription()
                , projectTo.getPayment() == null ? new BigDecimal(0) : projectTo.getPayment()
                , client
                , null
                , null
                , skills
                , null
        );
    }



//
//    public static Order updateFromTo(Order order, OrderTo orderTo) {
//        order.setCustomerName(orderTo.getFirstName());
//        order.setCustomerLastName(orderTo.getLastName());
//        order.setCustomerPhoneNumber(orderTo.getPhoneNumber());
//        order.setCustomerCity(orderTo.getCity());
//        order.setCustomerPostOffice(orderTo.getPostOffice());
//        order.setComment(orderTo.getComment());
//        order.setTotalSum(orderTo.getTotalSum());
//        return order;
//    }
//
//    public static int calculateTotalSumOfTos(Collection<OrderItemTo> orderItemTos){
//        return orderItemTos.stream().mapToInt(p -> (p.getPrice() * p.getQuantity())).sum();
//    }
//
//    public static int calculateTotalSum(Collection<OrderItem> orderItems){
//        return orderItems.stream().mapToInt(p -> (p.getProductPrice() * p.getProductQuantity())).sum();
//    }
}
