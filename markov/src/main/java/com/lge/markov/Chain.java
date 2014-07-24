package com.lge.markov;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Scanner;

import com.lge.markov.util.FixedColumnPrinter;
import com.lge.markov.util.TablePrinter;
import com.lge.markov.util.WordPrinter;

/**
 * Main Class for Markov Chain Algorithm. It keeps a database to generate a random text according to
 * the statistical model. The database can be built up using sample input text.
 * 
 * <pre>
 * Chain c = new Chain();
 * c.learn(System.in);
 * c.generate(System.out);
 * </pre>
 */
public class Chain {

    private static final int DEFAULT_GENERATE_LIMIT = 100;
    private static final int DEFAULT_SCREEN_WIDTH = 80;
    private final int PREFIX_LENGTH = 2;

    final String END_OF_TEXT = ""; // empty string is not read
    final Prefix BEGIN_OF_TEXT = new Prefix(PREFIX_LENGTH, "");

    final Random rand = new Random(0); // fixed a seed to generate identical text at every runs

    // TODO Add fields here if necessary

    /**
     * Updates database by learning provided inputStream.
     * 
     * @param inputStream
     */
    public void learn(InputStream inputStream) {
        Prefix prefix = new Prefix(BEGIN_OF_TEXT);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String word = scanner.next();
            // TODO using each word, update the prefix-suffix map
            System.out.println(word); // DELETE ME
        }
        // TODO add END_OF_TEXT here to stop generating properly
        scanner.close();
    }

    /**
     * Returns the count of words in database.
     * 
     * @return
     */
    public int getWordCount() {
        // TODO return the number of words in database
        return 1;
    }

    /**
     * Prints the contents of database for debugging purpose.
     * 
     * @param printer
     */
    public void printDatabase(TablePrinter printer) {
        // TODO set column width according to the longest prefix
        // TablePrinter usage:
        // printer.setColumnWidths(new int[]{ ... })
        // printer.printRow(...)
        System.out.println("Following table is sample. Delete it.");
        printer.setColumnWidths(new int[] { 10, 10 });
        for (String[] row : new String[][] { { "Column A", "Column B" }, { "A", "B" },
                { "AAAAA", "BBB" } }) {
            printer.printRow(row[0], row[1]);
        }
    }

    /**
     * Clear all data previously learned.
     */
    public void reset() {
        // TODO clear the database
    }

    /**
     * Generates random text according the statistical model.
     * 
     * @param maxLength
     *            upper limit of generated words
     * @param printer
     */
    public void generate(int maxLength, WordPrinter printer) {
        Prefix prefix = new Prefix(BEGIN_OF_TEXT);
        // TODO generate randomly selected suffix using current prefix
        // stop when the maxLength limit is hit or END_OF_TEXT is selected.
        // Use WordPrinter.print(word) to generate text.
        
        // DELETE ME
        printer.print("Hello");
        printer.print("World");
    }

    /**
     * Generates random text according the statistical model. By default it prints the text up to
     * 100 words using FixedColumnPrinter with 80 columns.
     * 
     * @param outputStream
     */
    public void generate(OutputStream outputStream) {
        generate(DEFAULT_GENERATE_LIMIT, new FixedColumnPrinter(outputStream, DEFAULT_SCREEN_WIDTH));
    }

    // TODO Add helper methods here if necessary
}
