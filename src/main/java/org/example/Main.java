package org.example;

import org.example.helpers.AppHelper;
import org.example.helpers.AppHelperGraphicsCard;
import org.example.helpers.AppHelperRegistry;
import org.example.helpers.AppHelperUser;
import org.example.model.GraphicsCard;
import org.example.model.Registry;
import org.example.model.User;
import org.example.repository.Repository;
import org.example.repository.Storage;
import org.example.services.GraphicsCardService;
import org.example.services.RegistryService;
import org.example.services.Service;
import org.example.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final Repository<GraphicsCard> graphicsCardRepository = new Storage<>("graphic cards");
        final Repository<Registry> registryRepository = new Storage<>("registry");
        final AppHelper<GraphicsCard> graphicsCardAppHelper = new AppHelperGraphicsCard(scanner);

        final Service<GraphicsCard> graphicsCardService = new GraphicsCardService(graphicsCardAppHelper, graphicsCardRepository);

        final Repository<User> userRepository = new Storage<>("users");
        final AppHelper<User> userAppHelper = new AppHelperUser(scanner);
        final Service<User> userService = new UserService(userAppHelper, userRepository);
        final AppHelper<Registry> registryAppHelper = new AppHelperRegistry(scanner, userService, graphicsCardService);
        final Service<Registry> registryService = new RegistryService(registryAppHelper, registryRepository);

        App app = new App(scanner, graphicsCardService, userService, registryService);
        app.run();
    }
}