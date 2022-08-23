package pham.ha.view;

import pham.ha.config.Config;
import pham.ha.controller.UserController;
import pham.ha.dto.request.SignUpDTO;
import pham.ha.dto.response.ResponseMessenger;
import pham.ha.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();

    public void menu() {
        System.out.println("*****MENU*****");
        System.out.println("1. Show user list");
        System.out.println("2. Register");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formShowListUser();
                break;
            case 2:
                formRegister();
                break;
            default:
                System.out.println("Invalid choice");

        }
        menu();
    }

    private void formRegister() {
        System.out.println("|---------Register---------|");
        //id
        int id;
        if (userList.isEmpty()) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }

        //name
        String name;
        while (true) {
            System.out.println("Enter name: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid name, try again!");
            }
        }

        //username
        String username;
        while (true) {
            System.out.println("Enter username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }

        //email
        String email;
        while (true) {
            System.out.println("Enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")) {
                break;
            } else {
                System.out.println("Invalid email, try again!");
            }
        }

        //password
        String password;
        while (true) {
            System.out.println("Enter password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }

        //role
        System.out.println("Enter role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, email, password, strRole);


        ResponseMessenger responseMessenger = userController.register(signUpDTO);

        switch (responseMessenger.getMessage()) {
            case "user_existed":
                System.out.println("Username already exists");
                break;
            case "email_existed":
                System.out.println("Email already exists");
                break;
            case "invalid_role":
                System.out.println("Invalid role, try again!");
                break;
            case "success":
                System.out.println("Register success");
                break;
        }
    }

    private void formShowListUser() {
        System.out.printf("%-15s%s%n", "Username", "Role");
        for (User user : userList) {
//            System.out.printf("%-15s%s%n", user.getUsername(), user.getRoles()); //Hiện tất cả ROLE!!!
            System.out.printf("%-15s%s%n", user.getUsername(), new ArrayList<>(user.getRoles()).get(0).getRoleName());//Hiện tất cả ROLE!!!
        }
    }
}

//"%-15s%s%n": khoảng cách