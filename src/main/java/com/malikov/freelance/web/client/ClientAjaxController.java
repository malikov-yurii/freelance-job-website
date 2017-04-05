package com.malikov.freelance.web.client;

import com.malikov.freelance.to.ClientTo;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/clients")
public class ClientAjaxController extends AbstractClientController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientTo> getAll() {
        return super.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/block")
    public void blockClient(@PathVariable("id") int clientId) {
        super.updateIsClientBlocked(clientId, true);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/unblock")
    public void unblockClient(@PathVariable("id") int clientId) {
        super.updateIsClientBlocked(clientId, false);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable("id") int clientId){
        super.deleteClient(clientId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void createOrUpdateClient(@Valid ClientTo clientTo){
        if (clientTo.isNew()) {
            super.create(clientTo);
        } else {
            super.update(clientTo);
        }
    }


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
