package com.malikov.freelance.util;

import com.malikov.freelance.model.*;
import com.malikov.freelance.to.ProjectTo;

import java.math.BigDecimal;
import java.util.List;


public class ProjectUtil {

    public static ProjectTo asTo(Project project, ApplicationStatus applicationStatus) {

        String skills = "";
        if (project.getRequiredSkills() != null && project.getRequiredSkills().size() != 0) {
            skills = project.getRequiredSkills().get(0).getName();
            for (int i = 1; i < project.getRequiredSkills().size(); i++) {
                skills += ", ";
                skills += project.getRequiredSkills().get(i).getName();
            }
        }


        return new ProjectTo(project.getId(), project.getClient().getId(), project.getName(),
                project.getDescription(), project.getPayment(), project.getClientLastName(),
                project.getStatus(), skills, applicationStatus);
    }

    public static Project fromTo(ProjectTo projectTo, Client client, List<Skill> skillsList) {
        return new Project(
                0
                , projectTo.getName() == null ? "No name provided" : projectTo.getName()
                , projectTo.getStatus() == null ? ProjectStatus.NEW : projectTo.getStatus()
                , projectTo.getDescription() == null ? "No description provided" : projectTo.getDescription()
                , projectTo.getPayment() == null ? new BigDecimal(0) : projectTo.getPayment()
                , client
                , null
                , null
                , skillsList);
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
