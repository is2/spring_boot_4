package habsida.spring.boot_security.demo.controller;


import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user-list";
    }


    @GetMapping("/admin/{id}")
    public String showOneUser(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-detail";
    }


    @GetMapping("/admin/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin"; }


    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-form";
    }


    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

