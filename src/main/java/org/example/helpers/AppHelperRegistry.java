package org.example.helpers;

import org.example.model.GraphicsCard;
import org.example.model.Registry;
import org.example.model.User;
import org.example.services.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppHelperRegistry implements AppHelper<Registry>{
    private final Scanner scanner;
    private final Service<User> userService;
    private final Service<GraphicsCard> graphicsCardService;

    public AppHelperRegistry(Scanner scanner, Service<User> userService, Service<GraphicsCard> graphicsCardService  ) {
        this.scanner = scanner;
        this.userService = userService;
        this.graphicsCardService = graphicsCardService;
    }

    @Override
    public Registry create() {
        try {
            Registry registry = new Registry();

            if(!userService.print()) {
                System.out.println("Users not found");
                return null;
            };

            System.out.print("Select user from list: ");
            scanner.nextLine();
            int userNumber = Integer.parseInt(scanner.nextLine());
            User user = userService.getRepository().load().get(userNumber-1);

            if(!graphicsCardService.print()){
                System.out.println("Graphic cards not found");
                return null;
            };

            System.out.print("Select graphics card from list: ");
            int gpuNumber = Integer.parseInt(scanner.nextLine());
            GraphicsCard graphicsCard = graphicsCardService.getRepository().load().get(gpuNumber-1);
            registry.setUser(user);
            registry.setGraphicsCard(graphicsCard);
            registry.setSellDate(LocalDate.now());
            return registry;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Registry edit(List<Registry> entities) {
        return null;
    }

    @Override
    public Registry delete(List<Registry> entities) {
        return null;
    }

    @Override
    public boolean printList(List<Registry> entities) {
        return false;
    }
}