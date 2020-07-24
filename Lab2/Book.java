/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab2 - Keeping out Sources Straight
 * Name: Stuart Harley
 * Created: 12/6/2018
 */

package harleys;

import java.util.Scanner;

/**
 * Represents bibliographical information about a book
 */
public class Book extends Reference {

    private String publisher;

    /**
     * Constructor for a book
     * @param author the author
     * @param title the title
     * @param publicationYear the publication year
     * @param publisher the publisher
     */
    public Book(String author, String title, int publicationYear, String publisher) {
        super(author, title, publicationYear);
        this.publisher = publisher;
    }

    /**
     * Prompts the user (via the console) to update the attributes of the book object.
     * @param in - Input stream to read user input
     */
    @Override
    public void promptForUpdate(Scanner in) {
        super.promptForUpdate(in);
        System.out.println("Enter the updated publisher of the book");
        publisher = in.nextLine();
    }

    /**
     * Returns a String of information in BibTeX format
     * @return String of BibTeX information
     */
    public String toString() {
        return "@BOOK { " + this.getMyUniqueID() + "\nauthor = " + this.getAuthor()
                + "\ntitle = " + this.getTitle() + "\npublisher = " + publisher
                + "\nyear = " + this.getPublicationYear() + "\n}\n";
    }
}
