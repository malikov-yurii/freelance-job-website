package com.malikov.freelance.web;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.to.BaseUserTo;
import com.malikov.freelance.to.ClientUserTo;
import com.malikov.freelance.to.FreelancerUserTo;
import com.malikov.freelance.to.ProjectSmallTo;
import com.malikov.freelance.util.ClientUtil;
import com.malikov.freelance.util.FreelancerUtil;
import com.malikov.freelance.util.SkillUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.awt.*;
import java.util.*;
import java.util.List;

@Controller
public class RootController extends AbstractController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @GetMapping("/freelancer-portfolio/{id}")
    public String freelancerPortfolio(@PathVariable("id") int freelancerId, ModelMap model) {
        super.addFreelancerToModel(freelancerId, model);
        model.addAttribute("portfolioList", super.getPortfolio(freelancerId));
        return "portfolio";
    }

    @GetMapping("/projects")
    public String orders() {
        return "projects";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/clients")
    public String clients() {
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

    @GetMapping("/profile")
    public String profile(ModelMap model) {
        model.addAttribute("register", false);
        model.addAttribute("profileUserTo", super.getAuthorizedUserTo());
        return "profile";
    }

    @PostMapping("/profile-freelancer")
    public String updateFreelancerProfile(@Valid FreelancerUserTo freelancerUserTo, ModelMap model) {
        super.updateFreelancerProfile(freelancerUserTo);
        model.addAttribute("register", false);
        model.addAttribute("profileUserTo", super.getAuthorizedUserTo());
        return "profile";
    }

    @PostMapping("/profile-user")
    public String updateClientAdminProfile(@Valid BaseUserTo baseUserTo, ModelMap model) {
        super.updateBaseUserProfile(baseUserTo);
        model.addAttribute("register", false);
        model.addAttribute("profileUserTo", super.getAuthorizedUserTo());
        return "profile";
    }

    @GetMapping("/register-client")
    public String registerClient(ModelMap model) {
        model.addAttribute("profileUserTo", new ClientUserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @GetMapping("/register-freelancer")
    public String registerFreelancer(ModelMap model) {
        model.addAttribute("profileUserTo", new FreelancerUserTo());
        model.addAttribute("register", true);
        return "profile";
    }

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
