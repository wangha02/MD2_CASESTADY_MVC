package pham.ha.service.User;

import pham.ha.model.User;
import pham.ha.service.IGenericService;

import java.util.List;

public interface IUserService extends IGenericService<User> {

    boolean exitsByUsername(String username);

    boolean exitsByEmail(String email);


}
