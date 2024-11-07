package org.example;

import org.example.model.GraphicsCard;
import org.example.model.Registry;
import org.example.model.User;
import org.example.services.Service;

import java.util.Scanner;

public class App {

    private final Scanner scanner;
    private final Service<GraphicsCard> graphicsCardService;
    private final Service<User> userService;
    private final Service<Registry> registryService;

    public App (Scanner scanner, Service<GraphicsCard> graphicsCardService, Service<User> userService, Service<Registry> registryService) {
        this.scanner = scanner;
        this.graphicsCardService = graphicsCardService;
        this.userService = userService;
        this.registryService = registryService;
    }

    public void run() {
        boolean repeat = true;
        System.out.println("------------GPU shop------------");

        do {
            System.out.println("Options list: ");
            System.out.println("0. Exit the program");
            System.out.println("1. Add new product");
            System.out.println("2. List of products");
            System.out.println("3. Add new client");
            System.out.println("4. List of clients");
            System.out.println("5. Buy product");
            System.out.println("6. Edit product");
            System.out.print("Select option from list: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    if(graphicsCardService.add()) {
                        System.out.println("The product has been added");
                    } else {
                        System.out.println("The product was not added");
                    }
                    break;
                case 2:
                    graphicsCardService.print();
                    break;
                case 3:
                    if(userService.add()) {
                        System.out.println("User has been added");
                    } else {
                        System.out.println("The user was not added");
                    }
                    break;
                case 4:
                    userService.print();
                    break;
                case 5:
                    if(registryService.add()) {
                        System.out.println("The gpu has been sold");
                    } else {
                        System.out.println("The gpu was not sold");
                    }
                case 6:
                    if(graphicsCardService.edit()) {
                        System.out.println("The product has been updated");
                    } else {
                        System.out.println("The product was not updated");
                    }
                    break;
                default:
                    System.out.println("Invalid option!");
            }

        } while (repeat);
        System.out.println("Goodbye!");
    }
}