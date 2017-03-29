package com.malikov.freelance.web.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ajax/profile/customers")
public class CustomerAjaxController extends AbstractCustomerController {

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<CustomerTo> getAll() {
//        return super.getAll();
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void delete(@PathVariable("id") int id) {
//        super.delete(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<String> updateOrCreate(@Valid CustomerTo customerTo, BindingResult result) {
//        if (result.hasErrors()) {
//            StringBuilder sb = new StringBuilder();
//            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
//            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//        if (customerTo.isNew()) {
//            super.create(CustomerUtil.createNewFromTo(customerTo));
//        } else {
//            com.malikov.freelance.model.Client customer = super.get(customerTo.getId());
//            super.update(CustomerUtil.updateFromTo(customer, customerTo), customerTo.getId());
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{id}/get-email")
//    public String getEmail(@PathVariable("id") int id) {
//        return super.getEmail(id);
//    }
//
//    @GetMapping(value = "/{id}/get-note")
//    public String getNote(@PathVariable("id") int id) {
//        return super.getNote(id);
//    }
}
