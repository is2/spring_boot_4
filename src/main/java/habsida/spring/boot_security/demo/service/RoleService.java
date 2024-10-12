package habsida.spring.boot_security.demo.service;



import habsida.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();

    public List<Role> findByIdRoles(List<Long> id);
}
