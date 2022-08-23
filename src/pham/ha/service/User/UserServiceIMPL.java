package pham.ha.service.User;

import pham.ha.config.Config;
import pham.ha.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {

    static String PATH_USER = "C:\\Users\\Admin\\IdeaProjects\\TinhKetQuaFizzBuzz\\untitled2\\md2\\src\\pham\\ha\\database\\user.txt";

    static Config<List<User>> config = new Config<>(); // tạo một đối tượng để đọc ghìile

    static List<User> userList = config.read(PATH_USER);

//    static List<User> userList = new ArrayList<>();

    //khi xóa dữ liệu trong file. khi list = null thì là rông thì gán cho nó một list mới
    static {
        if (userList == null) {
            userList =new ArrayList<>();
        }
    }

    @Override
    public List<User> findAll() {
        config.write(PATH_USER,userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        config.write(PATH_USER,userList);//%
    }

    @Override
    public boolean exitsByUsername(String username) { // kieemr tra user da ton tai hay chua
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean exitsByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
