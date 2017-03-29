package com.malikov.freelance.web.project;

import com.malikov.freelance.model.*;
import com.malikov.freelance.service.*;
import com.malikov.freelance.to.ProjectTo;
import com.malikov.freelance.util.ProjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.malikov.freelance.model.ApplicationStatus.*;

public abstract class AbstractProjectController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractProjectController.class);

    @Autowired
    private MessageSource messageSource;
    //
//    @Autowired
//    private FreelancerService orderService;
//
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SkillService skillService;

    //
//    @Autowired
//    private ClientService customerService;
//
//
//    @Autowired
//    private ProductVariationService productVariationService;
//
//    public OrderTo getOrderTo(int id) {
//        LOG.info("get order {}", id);
//        return OrderUtil.asTo(orderService.get(id));
//    }
//
//    public Order getOrder(int id) {
//        LOG.info("get order {}", id);
//        return orderService.get(id);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete order {}", id);
//        orderService.delete(id);
//    }
//
    public List<ProjectTo> getAll() {
        LOG.info("getAll orders");
        List<ProjectTo> projectTos = new ArrayList<>();

        ApplicationStatus applicationStatus;

        User authorizedUser = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!authorizedUser.getRoles().contains(Role.ROLE_FREELANCER)) {
            for (Project project : projectService.getAll())
                projectTos.add(ProjectUtil.asTo(project, ApplicationStatus.NOT_FREELANCER));
        } else {
            Freelancer authorizedFreelancer = freelancerService.get(authorizedUser.getId());
            for (Project project : projectService.getAll()) {
                if (project.getStatus() != ProjectStatus.LOOKING_FOR_FREELANCER) {
                    applicationStatus = NOT_LOOKING_FOR_A_FREELANCER;
                } else if (project.getAppliedFreelancers().contains(authorizedFreelancer)) {
                    applicationStatus = ALREADY_APPLIED;
                } else if (authorizedFreelancer.getSkills().containsAll(project.getRequiredSkills())) {
                    applicationStatus = ALLOWED_HAS_SKILLS;
                } else {
                    applicationStatus = NOT_ALLOWED_LACK_OF_SKILLS;
                }
                projectTos.add(ProjectUtil.asTo(project, applicationStatus));
            }
        }
        return projectTos;
    }

    public void applyForProject(int id) {
        Project project = projectService.get(id);
        project.getAppliedFreelancers().add(
                freelancerService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        projectService.save(project);
    }

    public void discardApplicationForProject(int id) {
        Project project = projectService.get(id);
        project.getAppliedFreelancers().remove(
                freelancerService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        projectService.save(project);
    }

    public void create(ProjectTo projectTo) {

        List<Skill> allSkills = skillService.getAll();
        List<Skill> rawSkillList = new ArrayList<>(
                Arrays.stream(
                        projectTo
                                .getRequiredSkills()
                                .split("\\W*,\\W*"))
                        .map(Skill::new)
                        .collect(Collectors.toList()));
        List<Skill> newProjectPersistedSkillList = new ArrayList<>();
        for (Skill skill : rawSkillList) {
            int skillId = allSkills.indexOf(skill);
            if (skillId == -1) {
                newProjectPersistedSkillList.add(skillService.save(skill));
            } else {
                newProjectPersistedSkillList.add(allSkills.get(skillId));
            }
        }

        projectService.save(ProjectUtil.fromTo(projectTo
                , clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName())
                , newProjectPersistedSkillList));
    }

    ;
//
//    public OrderDatatablePageTo getDatatablePage(int start, int length) {
//        LOG.info("getAll orders");
//        Long orderTotalQuantity = orderService.getTotalQuantity();
//        return new OrderDatatablePageTo(
//                orderTotalQuantity,
//                orderTotalQuantity,
//                orderService.getDatatablePage(start, length).stream().map(OrderUtil::asTo).collect(Collectors.toList()));
//    }
//
//    public void update(Order order, int id) {
//        order.setId(id);
//        LOG.info("update order{}", order);
//        orderService.update(order);
//    }
//
//    public Order create(Order order) {
//        order.setId(null);
//        LOG.info("create order{}", order);
//        return orderService.save(order);
//    }
//
//    public void updateOrderItemProductName(int itemId, String name) {
//        OrderItem orderItem = projectService.get(itemId);
//        orderItem.setProductName(name);
//        projectService.update(orderItem);
//    }
//
//    public int updateOrderItemPrice(int itemId, int price) {
//        OrderItem orderItem = projectService.get(itemId);
//        orderItem.setProductPrice(price);
//        Order order = orderItem.getOrder();
//        int totalSum = OrderUtil.calculateTotalSum(order.getOrderItems());
//        order.setTotalSum(totalSum);
//        orderService.update(order);
//        projectService.update(orderItem);
//        return totalSum;
//    }
//
//    public int updateOrderItemProductQuantity(int itemId, int quantity) {
//        OrderItem orderItem = projectService.get(itemId);
//        orderItem.setProductQuantity(quantity);
//        Order order = orderItem.getOrder();
//        int totalSum = OrderUtil.calculateTotalSum(order.getOrderItems());
//        order.setTotalSum(totalSum);
//        orderService.update(order);
//        projectService.update(orderItem);
//        return totalSum;
//    }
//
//    public int updateOrderItemPriceProductIdProductVariationId(int itemId, int price, int productId, int productVariationId, String orderItemName) {
//        OrderItem orderItem = projectService.get(itemId);
//        orderItem.setProductPrice(price);
//        orderItem.setProduct(productService.get(productId));
//        orderItem.setProductName(orderItemName);
//        if (productVariationId != 0)
//            orderItem.setProductVariation(productVariationService.get(productVariationId));
//        Order order = orderItem.getOrder();
//        int totalSum = OrderUtil.calculateTotalSum(order.getOrderItems());
//        order.setTotalSum(totalSum);
//        orderService.update(order);
//        projectService.update(orderItem);
//        return totalSum;
//    }
//
//    public List<CustomerAutocompleteTo> getCustomerAutocompleteTosByFirstNameMask(String firstNameMask) {
//        return customerService
//                .getByFirstNameMask(firstNameMask).stream().map(customer ->
//                        new CustomerAutocompleteTo(
//                                customer.getName() + " " + customer.getLastName() + " " + customer.getCity() + " " + customer.getPhoneNumber(),
//                                customer.getId(),
//                                customer.getName(),
//                                customer.getLastName(),
//                                customer.getPhoneNumber(),
//                                customer.getCity(),
//                                customer.getPostOffice()
//                        ))
//                .collect(Collectors.toList());
//    }
//
//    public List<CustomerAutocompleteTo> getCustomerAutocompleteTosByLastNameMask(String lastNameMask) {
//        return customerService
//                .getByLastNameMask(lastNameMask).stream().map(customer ->
//                        new CustomerAutocompleteTo(
//                                customer.getName() + " " + customer.getLastName() + " " + customer.getCity() + " " + customer.getPhoneNumber(),
//                                customer.getId(),
//                                customer.getName(),
//                                customer.getLastName(),
//                                customer.getPhoneNumber(),
//                                customer.getCity(),
//                                customer.getPostOffice()))
//                .collect(Collectors.toList());
//    }
//
//    public List<CustomerAutocompleteTo> getCustomerAutocompleteTosByPhoneNumberMask(String phoneNumberMask) {
//        return customerService
//                .getByPhoneNumberMask(phoneNumberMask).stream().map(customer ->
//                        new CustomerAutocompleteTo(
//                                customer.getName() + " " + customer.getLastName() + " " + customer.getCity() + " " + customer.getPhoneNumber(),
//                                customer.getId(),
//                                customer.getName(),
//                                customer.getLastName(),
//                                customer.getPhoneNumber(),
//                                customer.getCity(),
//                                customer.getPostOffice()))
//                .collect(Collectors.toList());
//    }
//
//    public List<CustomerAutocompleteTo> getCustomerAutocompleteTosByCityMask(String cityMask) {
//        return customerService
//                .getByCityMask(cityMask).stream().map(customer ->
//                        new CustomerAutocompleteTo(
//                                customer.getName() + " " + customer.getLastName() + " " + customer.getCity() + " " + customer.getPhoneNumber(),
//                                customer.getId(),
//                                customer.getName(),
//                                customer.getLastName(),
//                                customer.getPhoneNumber(),
//                                customer.getCity(),
//                                customer.getPostOffice()))
//                .collect(Collectors.toList());
//    }
//
//    public PaymentType[] getPaymentTypeAutocomplete() {
//        return PaymentType.values();
//    }
//
//    public Status[] getOrderStatusAutocomplete() {
//        return Status.values();
//    }
//
//    public List<OrderItemAutocompleteTo> getOrderItemAutocompleteTosByProductMask(String productNameMask) {
//        List<OrderItemAutocompleteTo> orderItemAutocompleteTos = new ArrayList<>();
//        productService.getByProductNameMask(productNameMask).forEach(product -> {
//            if (product.getHasVariations()) {
//                for (ProductVariation productVariation : product.getVariations()) {
//                    orderItemAutocompleteTos.add(
//                            new OrderItemAutocompleteTo(
//                                    product.getName() + " " + productVariation.getVariationValue().getName() + " " +
//                                            productVariation.getPrice(),
//                                    product.getId(),
//                                    productVariation.getId(),
//                                    product.getName() + " " + productVariation.getVariationValue().getName(),
//                                    productVariation.getPrice()
//                            )
//                    );
//                }
//            } else {
//                orderItemAutocompleteTos.add(
//                        new OrderItemAutocompleteTo(
//                                product.getName() + " " + product.getPrice(),
//                                product.getId(),
//                                0,
//                                product.getName(),
//                                product.getPrice()
//                        )
//                );
//            }
//        });
//        return orderItemAutocompleteTos;
//    }
//
//    public void addOrderItem(int orderId) {
//        projectService.save(new OrderItem(orderService.get(orderId), null, "", 0, 1));
//    }
//
//    public void deleteOrderItem(int orderItemId) {
//        projectService.delete(orderItemId);
//    }
//
//    public void updateOrderStatus(Integer orderId, Status status) {
//        // TODO: 2/6/2017 Make it work
////        orderService.updateStatus(orderId, status);
//        Order order = orderService.get(orderId);
//
//        order.setStatus(status);
//        orderService.save(order);
//    }
//
//    public void updateOrderComment(Integer orderId, String comment) {
//        // TODO: 2/6/2017 Make it work
////        orderService.updateStatus(orderId, status);
//        Order order = orderService.get(orderId);
//
//        order.setComment(comment);
//        orderService.save(order);
//    }
//
//    public void updateOrderPaymentType(Integer orderId, PaymentType paymentType) {
//        Order order = orderService.get(orderId);
//        order.setPaymentType(paymentType);
//        orderService.save(order);
//    }
//
//    public void updateFirstName(int orderId, String firstName) {
//        Order order = orderService.get(orderId);
//        order.setCustomerName(firstName);
//        orderService.save(order);
//    }
//
//    public void updateLastName(int orderId, String lastName) {
//        Order order = orderService.get(orderId);
//        order.setCustomerLastName(lastName);
//        orderService.save(order);
//    }
//
//    public void updatePhoneNumber(int orderId, String phoneNumber) {
//        Order order = orderService.get(orderId);
//        order.setCustomerPhoneNumber(phoneNumber);
//        orderService.save(order);
//    }
//
//    public void updateCity(int orderId, String city) {
//        Order order = orderService.get(orderId);
//        order.setCustomerCity(city);
//        orderService.save(order);
//    }
//
//    public void updatePostOffice(int orderId, String postOffice) {
//        Order order = orderService.get(orderId);
//        order.setCustomerPostOffice(postOffice);
//        orderService.save(order);
//    }
//
//    public void updateTotalSum(int orderId, Integer totalSum) {
//        Order order = orderService.get(orderId);
//        order.setTotalSum(totalSum);
//        orderService.save(order);
//    }
//
//    public void setCustomerForOrder(int orderId, int customerId) {
//        Order order = orderService.get(orderId);
//        Customer customer = customerService.get(customerId);
//        order.setCustomer(customer);
//        order.setCustomerName(customer.getName());
//        order.setCustomerLastName(customer.getLastName());
//        order.setCustomerPhoneNumber(customer.getPhoneNumber());
//        order.setCustomerCity(customer.getCity());
//        order.setCustomerPostOffice(customer.getPostOffice());
//        orderService.save(order);
//    }
//
//    public void persistCustomerFromOrder(int orderId) {
//        Order order = orderService.get(orderId);
//        if (order.getCustomer() != null) {
//            throw new DataIntegrityViolationException(messageSource.getMessage("exception.duplicateCustomer", null, LocaleContextHolder.getLocale()));
//        }
//        order.setCustomer(customerService.save(
//                new Customer(order.getCustomerName()
//                        , order.getCustomerLastName()
//                        , order.getCustomerPhoneNumber()
//                        , order.getCustomerCity()
//                        , order.getCustomerPostOffice()
//                        , null
//                        , null)));
//        orderService.save(order);
//    }
}