package pham.ha.service.Role;

import pham.ha.model.Role;
import pham.ha.model.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService{

    static List<Role> roleList = new ArrayList<>(); //1

    static { //2
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.USER));
    }

    @Override  //@1
    public List<Role> findAll() {
        return roleList;
    }

    @Override  //@2
    public void save(Role role) {
        roleList.add(role);

    }

    @Override  //@3
    public Role findByRoleName(RoleName roleName) {
        for (Role role : roleList) {
            if (role.getRoleName() == roleName) {
                return role;
            }
        }
        return null;
    }
}
