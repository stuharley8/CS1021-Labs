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
 * Represents bibliographical information about an article published in a journal
 */
public class Article extends Reference {

    private int startingPage;
    private int endingPage;
    private String journal;

    /**
     * Constructor for an article
     * @param author the author
     * @param title the title
     * @param publicationYear the publication year
     * @param journal the journal the article is published in
     * @param startingPage the starting page of the article in the journal
     * @param endingPage the ending page of the article in the jounral
     */
    public Article(String author, String title, int publicationYear, String journal,
                   int startingPage, int endingPage) {
        super(author, title, publicationYear);
        this.journal = journal;
        this.startingPage = startingPage;
        this.endingPage = endingPage;
    }

    /**
     * Prompts the user (via the console) to update the attributes of the article object.
     * @param in - Input stream to read user input
     */
    @Override
    public void promptForUpdate(Scanner in) {
        super.promptForUpdate(in);
        System.out.println("Enter the updated starting page of the article");
        startingPage = in.nextInt();
        System.out.println("Enter the updated ending page of the article");
        endingPage = in.nextInt();
        in.nextLine();
        System.out.println("Enter the updated journal of the article");
        journal = in.nextLine();
    }

    /**
     * Returns a String of information in BibTeX format
     * @return String of BibTeX information
     */
    public String toString() {
        return "@BOOK { " + this.getMyUniqueID() + "\nauthor = " + this.getAuthor()
                + "\ntitle = " + this.getTitle() + "\njournal = " + journal
                + "\npages = " + startingPage + "-" + endingPage
                + "\nyear = " + this.getPublicationYear() + "\n}\n";
    }
}
