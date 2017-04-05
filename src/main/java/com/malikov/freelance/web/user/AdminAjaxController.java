package com.malikov.freelance.web.user;

import com.malikov.freelance.model.Role;
import com.malikov.freelance.model.User;
import com.malikov.freelance.to.BaseUserTo;
import com.malikov.freelance.util.UserUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/ajax/profile/admins")
public class AdminAjaxController extends AbstractUserController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BaseUserTo> getAllAdmins() {
        return super.getAllAdmins();
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
    public void delete(@PathVariable("id") int clientId) {
        super.delete(clientId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void createOrUpdate(@Valid BaseUserTo baseUserTo) {
        if (baseUserTo.isNew()) {
            User user = UserUtil.newFromTo(baseUserTo);
            user.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
            super.create(user);
        } else {
            super.update(baseUserTo);
        }
    }

}
