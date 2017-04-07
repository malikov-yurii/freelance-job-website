package com.malikov.freelance.web;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.model.Role;
import com.malikov.freelance.model.User;
import com.malikov.freelance.service.ClientService;
import com.malikov.freelance.service.FreelancerService;
import com.malikov.freelance.service.UserService;
import com.malikov.freelance.to.ClientUserTo;
import com.malikov.freelance.to.FreelancerUserTo;
import com.malikov.freelance.util.ClientUtil;
import com.malikov.freelance.util.FreelancerUtil;
import com.malikov.freelance.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class RootController extends AbstractController{

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FreelancerService freelancerService;

    //    @Autowired
//    private UserService service;
//
//    @GetMapping("/")
//    public String root() {
//        return "redirect:orders";
//    }
//
//    //    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/users")
//    public String users() {
//        return "users";
//    }
//
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    //
//    @GetMapping("/products")
//    public String products() {
//        return "products";
//    }
//
    @GetMapping("/projects")
    public String orders(ModelMap model) {
        User user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.put("userId", user.getId());
        if (user.getRoles().contains(Role.ROLE_ADMIN))
            model.put("userRole", "admin");
        else if (user.getRoles().contains(Role.ROLE_CLIENT))
            model.put("userRole", "client");
        else if (user.getRoles().contains(Role.ROLE_FREELANCER))
            model.put("userRole", "freelancer");
        return "projects";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/clients")
    public String clients(
//            ModelMap model
    ) {
//        User user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        model.put("userId", user.getId());
//        model.put("userRole", "admin");
        return "clients";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admins")
    public String admins() {
        return "admins";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/freelancers")
    public String freelancers() {
        return "freelancers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/skills")
    public String skills() {
        return "skills";
    }
//
//    @GetMapping("/customers")
//    public String customers() {
//        return "customers";
//    }
//
//    @GetMapping("/profile")
//    public String profile() {
//        return "profile";
//    }
//
//    @PostMapping("/profile")
//    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
//        if (result.hasErrors()) {
//            return "profile";
//        } else {
//            userTo.setId(AuthorizedUser.id());
//            service.update(userTo);
//            AuthorizedUser.get().update(userTo);
//            status.setComplete();
//            return "redirect:products";
//        }
//    }
//
    @GetMapping("/register-client")
    public String registerClient(ModelMap model) {
        model.addAttribute("userTo", new ClientUserTo());
        model.addAttribute("register", true);
        model.addAttribute("userRole", "client");
        return "profile";
    }

    @GetMapping("/register-freelancer")
    public String registerFreelancer(ModelMap model) {
        model.addAttribute("userTo", new FreelancerUserTo());
        model.addAttribute("register", true);
        model.addAttribute("userRole", "freelancer");
        return "profile";
    }
//
    @PostMapping("/register-client")
    public String saveRegisteredClient(@Valid ClientUserTo clientUserTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            Client client = ClientUtil.newFromTo(clientUserTo);
            client.setId(null);
            clientService.save(client);
            status.setComplete();
            return "redirect:login?message=app.registered";
        }
    }

    @PostMapping("/register-freelancer")
    public String saveRegisteredFreelancer(@Valid FreelancerUserTo freelancerUserTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            Freelancer freelancer = FreelancerUtil.newFromTo(freelancerUserTo, persistNewSkills(SkillUtil.getSkillSetFromString(freelancerUserTo.getSkills())));
            freelancer.setId(null);
            freelancerService.save(freelancer);
            status.setComplete();
            return "redirect:login?message=app.registered";
        }
    }
}
