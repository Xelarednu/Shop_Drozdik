package org.example.helpers;

import org.example.model.User;

import java.util.List;
import java.util.Scanner;

public class AppHelperUser implements AppHelper<User>{
    private final Scanner scanner;

    public AppHelperUser(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public User create(){
        User user = new User();

        scanner.nextLine();

        System.out.print("First name: ");
        user.setFirstName(scanner.nextLine());

        System.out.print("Last name: ");
        user.setLastName(scanner.nextLine());

        System.out.print("Email: ");
        user.setEmail(scanner.nextLine());

        return user;
    }

    @Override
    public User edit(List<User> entities) {
        return null;
    }

    @Override
    public User delete(List<User> entities) {
        return null;
    }

    @Override
    public boolean printList(List<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println(" --- List of customers is empty --- ");
            return false;
        } else {
            System.out.println(" --- List of customers --- ");
            for (int i = 0; i < users.size(); i++) {
                System.out.printf("%d. %s %s. %s%n", i + 1, users.get(i).getFirstName(), users.get(i).getLastName(), users.get(i).getEmail());
            }
            System.out.println(" --- End of list --- ");
            return true;
        }
    }
}