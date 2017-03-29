package com.malikov.freelance.web.project;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = OrderRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectRestController extends AbstractProjectController {

//    static final String REST_URL = "/rest/profile/orders";
//
//    @GetMapping("/{id}")
//    public OrderTo get(@PathVariable("id") int id) {
//        return super.getOrderTo(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") int id) {
//        super.delete(id);
//    }
//
//    @GetMapping
//    public List<OrderTo> getAll() {
//        return super.getAll();
//    }
//
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(Order order, @PathVariable("id") int id) {
//        super.update(order, id);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Order> createWithLocation(@RequestBody Order order) {
//        Order created = super.create(order);
//
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
}