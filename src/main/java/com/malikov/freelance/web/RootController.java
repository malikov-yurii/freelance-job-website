package com.malikov.freelance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RootController {

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
    public String orders() {
        return "projects";
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
//    @GetMapping("/register")
//    public String register(ModelMap model) {
//        model.addAttribute("userTo", new UserTo());
//        model.addAttribute("register", true);
//        return "profile";
//    }
//
//    @PostMapping("/register")
//    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
//        if (result.hasErrors()) {
//            model.addAttribute("register", true);
//            return "profile";
//        } else {
//            User user = UserUtil.createNewFromTo(userTo);
//            user.setId(null);
//            service.save(user);
//            status.setComplete();
//            return "redirect:login?message=app.registered";
//        }
//    }
}
