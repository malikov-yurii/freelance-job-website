package com.malikov.freelance.web.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCustomerController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCustomerController.class);
//
//    @Autowired
//    private ClientService service;
//
//    public com.malikov.freelance.model.Client get(int id) {
//        LOG.info("get order {}", id);
//        return service.get(id);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete order {}", id);
//        service.delete(id);
//    }
//
//    public List<CustomerTo> getAll() {
//        LOG.info("getAll customers");
//        return service.getAll().stream().map(CustomerUtil::asTo).collect(Collectors.toList());
//    }
//
//    public void update(com.malikov.freelance.model.Client customer, int id) {
//        customer.setId(id);
//        LOG.info("update order{}", customer);
//        service.update(customer);
//    }
//
//    public com.malikov.freelance.model.Client create(com.malikov.freelance.model.Client customer) {
//        customer.setId(null);
//        LOG.info("create order{}", customer);
//        return service.save(customer);
//    }
//
//    public String getEmail(int id) {
//        return service.get(id).getEmail();
//    }
//    public String getNote(int id) {
//        return service.get(id).getNote();
//    }
}