package pham.ha.service.Role;


import pham.ha.model.Role;
import pham.ha.model.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    Role findByRoleName(RoleName roleName);

}
