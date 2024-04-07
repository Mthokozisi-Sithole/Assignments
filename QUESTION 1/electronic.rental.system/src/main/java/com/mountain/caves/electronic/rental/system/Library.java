package com.mountain.caves.electronic.rental.system; // Package declaration

import java.util.List;
import java.util.ArrayList;

// Library class represents a library with a collection of books
class Library {
    private String address; // Address of the library
    private List<Book> books; // List to store books in the library

    // Constructor to initialize the library with an address
    public Library(String address) {
        this.address = address; // Set the address
        this.books = new ArrayList<>(); // Initialize the list of books
    }

    // Method to add a book to the library
    public void addBook(Book book) {
        books.add(book); // Add the book to the list of books
    }

    // Method to print available books in the library
    public void printAvailableBooks() {
        // Print header for the table of available books
        System.out.println("\nBooks available at " + address + ":");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-50s | %-15s | %-10s |\n", "Title", "ISBN", "Available");
        System.out.println("--------------------------------------------------------------");

        // Iterate through the list of books
        for (Book book : books) {
            if (book.isAvailable()) { // Check if the book is available
                // Print the book details in a formatted table row
                System.out.printf("| %-50s | %-15s | %-10s |\n", book.getTitle(), book.getISBN(), "Yes");
            }
        }

        // Print footer for the table
        System.out.println("--------------------------------------------------------------");
    }

    // Method to borrow a book from the library
    public void borrowBook(String title) {
        // Iterate through the list of books
        for (Book book : books) {
            // Check if the title of the book starts with the provided title and if it's available
            if (book.getTitle().startsWith(title) && book.isAvailable()) {
                book.borrow(); // Borrow the book
                return;
            }
        }
        // If the book is not found or not available, print a message
        System.out.println("Book \"" + title + "\" not found or not available in the library at " + address + ".");
    }

    // Method to return a book to the library
    public void returnBook(String title) {
        // Iterate through the list of books
        for (Book book : books) {
            // Check if the title of the book starts with the provided title and if it's not available
            if (book.getTitle().startsWith(title) && !book.isAvailable()) {
                book.returnBook(); // Return the book
                return;
            }
        }
        // If the book is not found or already available, print a message
        System.out.println("Book \"" + title + "\" not found or already available in the library at " + address + ".");
    }
}
