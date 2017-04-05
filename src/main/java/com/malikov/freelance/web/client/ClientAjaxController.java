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
        super.blockUnblock(clientId, true);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{id}/unblock")
    public void unblockClient(@PathVariable("id") int clientId) {
        super.blockUnblock(clientId, false);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int clientId){
        super.delete(clientId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void createOrUpdate(@Valid ClientTo clientTo){
        if (clientTo.isNew()) {
            super.create(clientTo);
        } else {
            super.update(clientTo);
        }
    }

}
