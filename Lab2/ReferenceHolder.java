/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab2 - Keeping out Sources Straight
 * Name: Stuart Harley
 * Created: 12/6/2018
 */

package harleys;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to hold all the BibTeX references
 */
public class ReferenceHolder {

    private ArrayList<Reference> references;

    /**
     * Default constructor for a reference holder
     */
    public ReferenceHolder() {
        references = new ArrayList<>();
    }

    /**
     * Add an article reference to the bibliography
     * @param article - the article reference to be added
     */
    public void addReference(Article article) {
        references.add(article);
    }

    /**
     * Add a book reference to the bibliography
     * @param book - the book reference to be added
     */
    public void addReference(Book book) {
        references.add(book);
    }

    /**
     * Return a string containing all BibTeX entries in the Reference list
     * @return a string containing all BibTeX entries
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Reference reference : references) {
            output.append(reference.toString());
        }
        return output.toString();
    }

    /**
     * Asks the reference corresponding to the uniqueID to update itself
     * @param uniqueID - the id for the reference to be added
     * @param in - Input stream to read user input
     */
    public void updateReference(String uniqueID, Scanner in) {
        for (Reference reference : references) {
            if(uniqueID.equals(reference.getMyUniqueID())) {
                reference.promptForUpdate(in);
                break;
            }
        }
    }
}

/*
    1) The is aggregation between the ReferenceHolder and Reference Classes

    2) The only static methods are in the Driver Class

    3) The myUniqueID attribute is read only because the ID number of each
    reference should not be changed or it could screw up the program
*/