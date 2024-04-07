package com.mountain.caves.electronic.rental.system; // Package declaration

import java.util.Random;
import java.util.Scanner;

// Book class represents a book with a title, ISBN, and availability status
class Book {
    private String title; // Title of the book
    private String isbn; // ISBN of the book
    private boolean available; // Availability status of the book

    // Constructor to initialize a book with a title
    public Book(String title) {
        this.title = title; // Set the title
        this.isbn = generateISBN(); // Generate a random ISBN
        this.available = true; // Initially, the book is available
    }

    // Method to get the title of the book
    public String getTitle() {
        return title;
    }

    // Method to get the ISBN of the book
    public String getISBN() {
        return isbn;
    }

    // Method to check if the book is available
    public boolean isAvailable() {
        return available;
    }

    // Method to borrow the book
    public void borrow() {
        if (available) {
            available = false; // Mark the book as not available
            System.out.println("Book \"" + title + "\" with ISBN " + isbn + " has been borrowed.");
        } else {
            System.out.println("Sorry, the book \"" + title + "\" is not available.");
        }
    }

    // Method to return the book
    public void returnBook() {
        if (!available) {
            available = true; // Mark the book as available
            System.out.println("Book \"" + title + "\" with ISBN " + isbn + " has been returned.");
        } else {
            System.out.println("The book \"" + title + "\" is already available.");
        }
    }

    // Method to generate a random ISBN for the book
    private String generateISBN() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
