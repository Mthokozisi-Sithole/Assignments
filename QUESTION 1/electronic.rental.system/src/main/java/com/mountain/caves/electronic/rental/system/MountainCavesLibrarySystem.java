package com.mountain.caves.electronic.rental.system; // Package declaration

import java.util.Random;
import java.util.Scanner;

// Main class of the library system
public class MountainCavesLibrarySystem {

    public static void main(String[] args) {
        // Creating two library objects
        Library library1 = new Library("Mountain Caves Library 1");
        Library library2 = new Library("Mountain Caves Library 2");

        // Adding random books to libraries
        addRandomBooks(library1, 5);
        addRandomBooks(library2, 5);

        // Creating scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            System.out.println("\nWelcome to Mountain Caves Library System!");
            System.out.println("1. Print available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Print available books in both libraries
                    library1.printAvailableBooks();
                    library2.printAvailableBooks();
                    break;
                case 2:
                    // Borrow a book
                    System.out.print("Enter the title of the book you want to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    System.out.print("Enter the library from which you are borrowing (1 or 2): ");
                    int borrowLibraryNum = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Library borrowLibrary = borrowLibraryNum == 1 ? library1 : library2;
                    borrowLibrary.borrowBook(borrowTitle);
                    break;
                case 3:
                    // Return a book
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnTitle = scanner.nextLine();
                    System.out.print("Enter the library to which you are returning (1 or 2): ");
                    int returnLibraryNum = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Library returnLibrary = returnLibraryNum == 1 ? library1 : library2;
                    returnLibrary.returnBook(returnTitle);
                    break;
                case 4:
                    // Exit the program
                    System.out.println("Thank you for using Mountain Caves Library System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    // Method to add random books to a library
    private static void addRandomBooks(Library library, int numBooks) {
        Random random = new Random();
        for (int i = 0; i < numBooks; i++) {
            // Generate random title and add book to the library
            String title = "Book " + (i + 1) + " - Title: " + generateRandomTitle();
            library.addBook(new Book(title));
        }
    }

    // Method to generate a random title for a book
    private static String generateRandomTitle() {
        String[] titles = {
                "Congress and National Assembly",
                "The Great Gatsby",
                "To Kill a Mockingbird",
                "1984",
                "Pride and Prejudice",
                "The Catcher in the Rye",
                "Animal Farm",
                "Lord of the Flies",
                "Brave New World",
                "The Hobbit"
        };
        Random random = new Random();
        return titles[random.nextInt(titles.length)];
    }
}