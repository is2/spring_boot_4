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
    public String showAllUsers() {
        return "user-list";
    }


    @GetMapping("/admin/{id}")
    public String showOneUser() {
        return "user-detail";
    }


    @GetMapping("/admin/add")
    public String showAddForm() {
        return "user-form";
    }

    @PostMapping
    public String addUser() {
        return "redirect:/admin"; }


    @GetMapping("/admin/edit/{id}")
    public String showEditForm() {
        return "user-form";
    }


    @PostMapping("/admin/update")
    public String updateUser() {
        return "redirect:/admin";
    }


    @GetMapping("/admin/delete/{id}")
    public String deleteUser() {
        return "redirect:/admin";
    }
}

