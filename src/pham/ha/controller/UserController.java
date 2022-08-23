package pham.ha.controller;

import pham.ha.dto.request.SignUpDTO;
import pham.ha.dto.response.ResponseMessenger;
import pham.ha.model.Role;
import pham.ha.model.RoleName;
import pham.ha.model.User;
import pham.ha.service.Role.IRoleService;
import pham.ha.service.Role.RoleServiceIMPL;
import pham.ha.service.User.IUserService;
import pham.ha.service.User.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {

    IUserService userService = new UserServiceIMPL();

    IRoleService roleService = new RoleServiceIMPL();

    public List<User> getUserList() { // lay tu service
        return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.exitsByUsername(signUpDTO.getUsername())) {
            return new ResponseMessenger("user_existed");
        }
        if (userService.exitsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        for (String role : strRole) {
            switch (role) {
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("invalid_role");
            }
        }

        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles

        );

        userService.save(user);

        return new ResponseMessenger("success");
    }
}
