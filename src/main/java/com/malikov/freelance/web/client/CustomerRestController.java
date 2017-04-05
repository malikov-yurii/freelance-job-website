package com.malikov.freelance.web.client;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = CustomerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController extends AbstractClientController {

//    static final String REST_URL = "/rest/profile/customers";
//
//    @GetMapping(value = "/{id}")
//    public CustomerTo getCustomerTo(@PathVariable("id") int id) {
//        return CustomerUtil.asTo(super.get(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") int id) {
//        super.delete(id);
//    }
//
//    @GetMapping
//    public List<CustomerTo> getAll() {
//        return super.getAll();
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<com.malikov.freelance.model.Client> createWithLocation(@RequestBody com.malikov.freelance.model.Client customer) {
//        com.malikov.freelance.model.Client created = super.create(customer);
//
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
//
//    //    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
////    public ResponseEntity<String> update(@Valid  CustomerTo customerTo, @PathVariable("id") int id, BindingResult result) {
////        if (result.hasErrors()) {
////            StringBuilder sb = new StringBuilder();
////            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
////            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
////        }
////        super.update(CustomerUtil.createNewFromTo(customerTo), id);
////        return new ResponseEntity<>(HttpStatus.OK);
////    }
////    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PostMapping(value = "/{id}")
//    public void update(@Valid  CustomerTo customerTo, @PathVariable("id") int id) {
//
//        super.update(CustomerUtil.createNewFromTo(customerTo), id);
//
//    }
}