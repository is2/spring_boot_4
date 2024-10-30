package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class IndexRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public IndexRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public ResponseEntity<String> getPage() {
        return ResponseEntity.ok("Main page content");
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, Boolean>> getLogin(@RequestParam(value = "error", required = false) String error,
                                                         @RequestParam(value = "logout", required = false) String logout) {
        return ResponseEntity.ok(Map.of(
                "error", error != null,
                "logout", logout != null
        ));
    }

    @GetMapping("/registration")
    public ResponseEntity<Map<String, Object>> registration() {
        return ResponseEntity.ok(Map.of(
                "userFormRegistration", new User(),
                "allRoles", roleService.getAllRoles()
        ));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> addNewUser(@RequestBody User user,
                                             @RequestParam("roles") List<Long> roleIds) {
        List<Role> roles = roleService.findByIdRoles(roleIds);
        user.setRoles(roles);
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}

