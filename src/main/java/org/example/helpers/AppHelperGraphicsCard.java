package org.example.helpers;

import org.example.model.GraphicsCard;

import java.util.List;
import java.util.Scanner;

public class AppHelperGraphicsCard implements AppHelper<GraphicsCard> {
    private final Scanner scanner;

    public AppHelperGraphicsCard(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public GraphicsCard create() {
        try {
            System.out.println("===== New graphics card =====");
            GraphicsCard graphicsCard = new GraphicsCard();
            scanner.nextLine();

            System.out.println("Graphics card name: ");
            graphicsCard.setName(scanner.nextLine());

            System.out.println("Graphics card price: ");
            graphicsCard.setPrice(Integer.parseInt(scanner.nextLine()));

            System.out.println("Graphics card manufacturer: ");
            graphicsCard.setManufacturer(scanner.nextLine());

            System.out.println("Graphics card memory size: ");
            graphicsCard.setMemorySize(Integer.parseInt(scanner.nextLine()));

            System.out.println("Graphics card release year: ");
            graphicsCard.setReleaseYear(Integer.parseInt(scanner.nextLine()));

            return graphicsCard;
        } catch (Exception e){
            System.out.println("Error: "+e.toString());
            return null;
        }
    }

    @Override
    public GraphicsCard edit(List<GraphicsCard> graphicsCards) {
        System.out.println("===== Change graphics card data =====");

        printList(graphicsCards);
        System.out.println("Select graphics card to edit: ");
        int gpuNumber = scanner.nextInt();

        GraphicsCard selectedGpu = graphicsCards.get(gpuNumber - 1);

        System.out.println("Change graphics card manufacturer?(y/n)");
        scanner.nextLine();
        if (scanner.nextLine().trim().equalsIgnoreCase ("y")) {
            System.out.println("Enter card's new manufacturer: ");
            scanner.nextLine();
            selectedGpu.setManufacturer(scanner.nextLine());
        }

        System.out.println("Change graphics card name?(y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase ("y")) {
            System.out.println("Enter card's new name: ");
            scanner.nextLine();
            selectedGpu.setName(scanner.nextLine());
        }

        System.out.println("Change graphics card price?(y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase ("y")) {
            System.out.println("Enter card's new price: ");
            scanner.nextLine();
            selectedGpu.setPrice(scanner.nextInt());
        }

        System.out.println("Change graphics card memory size?(y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase ("y")) {
            System.out.println("Enter card's new memory size: ");
            scanner.nextLine();
            selectedGpu.setMemorySize(scanner.nextInt());
        }

        System.out.println("Change graphics card release year?(y/n)");
        if (scanner.nextLine().trim().equalsIgnoreCase ("y")) {
            System.out.println("Enter card's new release year: ");
            scanner.nextLine();
            selectedGpu.setReleaseYear(scanner.nextInt());
        }

        return selectedGpu;
    }

    @Override
    public GraphicsCard delete(List<GraphicsCard> graphicsCards) {
        printList(graphicsCards);
        System.out.print("Select GPU to delete: ");
        int gpuNumber = scanner.nextInt();

        GraphicsCard selectedGpu = graphicsCards.get(gpuNumber - 1);

        return selectedGpu;
    }

    @Override
    public boolean printList(List<GraphicsCard> graphicsCards) {
        if (graphicsCards == null || graphicsCards.isEmpty()){
            System.out.println(" --- List is empty --- ");
            return false;
        } else {
            System.out.println(" --- List of graphic cards --- ");
            for (int i = 0; i < graphicsCards.size(); i++){
//                StringBuilder sb = new StringBuilder();

                System.out.printf("%d. %s %s. %.2f %d GB. Released: %d %n", i+1, graphicsCards.get(i).getManufacturer(), graphicsCards.get(i).getName(), graphicsCards.get(i).getPrice() ,graphicsCards.get(i).getMemorySize(), graphicsCards.get(i).getReleaseYear());
            }
            System.out.println(" --- End list --- ");
            return true;
        }
    }
}