package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws URISyntaxException, IOException {
        Scanner scanner = new Scanner(System.in);
        ChatGPTHelper chatGPTHelper = new ChatGPTHelper();

        while (true) {
            System.out.println("Choose option");
            System.out.println("1.List products");
            System.out.println("2.Add products");
            System.out.println("3.Delete product");
            System.out.println("4.Three breakfast ideas");
            System.out.println("5.Three dinner ideas");
            System.out.println("6.Recommend products to buy");
            System.out.println("7.Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            ProductMenager productMenager = new ProductMenager();

            switch (choice) {
                case 1 -> {
                    System.out.println("Products: ");
                    productMenager.getAllProducts().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Which product you want to add");
                    String product = scanner.nextLine();
                    productMenager.addProduct(product); 
                }
                case 3 -> {
                    System.out.println("Which product you want to remove");
                    String product = scanner.nextLine();
                    productMenager.deleteProduct(product);
                }
                case 4 -> {
                    System.out.println("Breakfast ideas: ");
                    String breakfastIdea = chatGPTHelper.getBreakfastIdea(productMenager.getAllProducts());
                    System.out.println(breakfastIdea);
                }
                case 5 -> {
                    System.out.println("Lunch ideas: ");
                    String lunchIdea = chatGPTHelper.getLunchIdea(productMenager.getAllProducts());
                    System.out.println(lunchIdea);
                }
                case 6 -> {
                    System.out.println("Products recommended to buy: ");
                    String recommendation = chatGPTHelper.getHealthyProductsRecommendation(productMenager.getAllProducts());
                    System.out.println(recommendation);
                }
                case 7 -> {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
            }
        }
    }
}