package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public IndexController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getPage(){
        return "main-page";
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error!=null);
        model.addAttribute("logout", logout!=null);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userFormRegistration", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("userFormRegistration") User user,
                             @RequestParam("roles") List<Long> roleIds) {
        List<Role> roles = roleService.findByIdRoles(roleIds);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/login";
    }
}

