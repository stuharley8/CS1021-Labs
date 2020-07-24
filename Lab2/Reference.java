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
 * Represents bibliographical information about a reference
 */
public class Reference {

    private static int instanceCount = 0;
    private String author;
    private final String myUniqueID;
    private int publicationYear;
    private String title;

    /**
     * Constructor for a reference
     * @param author the author
     * @param title the title
     * @param publicationYear the publication year
     */
    public Reference(String author, String title, int publicationYear) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        myUniqueID = "REF" + instanceCount;
        instanceCount++;
    }

    public String getAuthor() {
        return author;
    }

    public String getMyUniqueID() {
        return myUniqueID;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Prompts the user (via the console) to update the attributes of the reference object.
     * @param in - Input stream to read user input
     */
    public void promptForUpdate(Scanner in) {
        System.out.println("Enter the updated author of the reference");
        author = in.nextLine();
        System.out.println("Enter the updated title of the reference");
        title = in.nextLine();
        System.out.println("Enter the updated publication year of the reference");
        publicationYear = in.nextInt();
        in.nextLine();
    }
}
