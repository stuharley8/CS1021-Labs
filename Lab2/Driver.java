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
 * A simple test program for CS1021 laboratory assignment 2
 * @author schilling with minor edits by taylor and riley
 * @version 1.3-20181129
 */

public class Driver {

    /**
     * This method provides a menu to obtain book and article references and then
     * display them to the console window.
     * @param args ignored
     */
    public static void main(String[] args) {
        ReferenceHolder references = new ReferenceHolder();
        int choice;
        Scanner in = new Scanner(System.in);

        do {
            printPrompt();
            while (!in.hasNextInt()) {
                in.next();
                printPrompt();
            }
            choice = in.nextInt();
            in.nextLine(); // consumes the carriage return from the user
            if(choice == 1) {
                Book book = createBookEntry(in);
                references.addReference(book);
            } else if(choice == 2) {
                Article article = createArticleEntry(in);
                references.addReference(article);
            } else if(choice == 3) {
                System.out.println("Enter the ID of the reference you want to update");
                String uniqueID = in.nextLine();
                references.updateReference(uniqueID, in);
            } else if(choice == 4) {
                System.out.print(references);
            } else {
                if(choice != 0) {
                    System.out.println("Please select a valid input");
                }
            }
        } while (choice != 0);
        System.out.println("Goodbye");
    }

    /**
     * This method displays main menu to the console.
     */
    private static void printPrompt() {
        System.out.println("Enter 0 to exit the program.");
        System.out.println("Enter 1 to enter a new book into the reference set.");
        System.out.println("Enter 2 to enter a new article into the reference set.");
        System.out.println("Enter 3 to update a reference.");
        System.out.println("Enter 4 to printout the entries in Bibtex format.");
    }

    /**
     * This method will allow the user to enter the information for a book.
     * @param in This is the keyboard.
     * @return A book will be returned.
     */
    private static Book createBookEntry(Scanner in) {
        System.out.println("Enter the author of the book");
        String author = in.nextLine();

        System.out.println("Enter the title of the book");
        String title = in.nextLine();

        System.out.println("Enter the publisher for the book.");
        String publisher = in.nextLine();

        System.out.println("Enter the copyright year for the book.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the copyright year for the book.");
        }
        int copyright = in.nextInt();
        in.nextLine();

        return new Book(author, title, copyright, publisher);
    }

    /**
     * This method will allow the user to enter the information for an article.
     * @param in This is the keyboard.
     * @return An article will be returned.
     */
    private static Article createArticleEntry(Scanner in) {
        System.out.println("Enter the author of the article");
        String author = in.nextLine();

        System.out.println("Enter the title of the article");
        String title = in.nextLine();

        System.out.println("Enter the title of the journal.");
        String journal = in.nextLine();

        System.out.println("Enter the first page of the article.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the first page of the article.");
        }
        int start = in.nextInt();

        System.out.println("Enter the last page of the article.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the last page of the article.");
        }
        int end = in.nextInt();
        in.nextLine();

        System.out.println("Enter the copyright year for the article.");
        while(!in.hasNextInt()) {
            in.next();
            System.out.println("Enter the copyright year for the article.");
        }
        int copyright = in.nextInt();
        in.nextLine();

        return new Article(author, title, copyright, journal, start, end);
    }

}