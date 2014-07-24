package com.lge.markov;

import com.lge.markov.util.FixedColumnPrinter;
import com.lge.markov.util.TablePrinter;
import com.lge.markov.util.WordPrinter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Stream;

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

    Map<Prefix, Set<String>> suffixMap = new HashMap<>();

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
            addSuffix(prefix, word);
            prefix = new Prefix(prefix, word);
        }
        addSuffix(prefix, END_OF_TEXT);
        scanner.close();
    }

    private void addSuffix(Prefix prefix, String suffix) {
        Set<String> suffices = suffixMap.get(prefix);
        if (null == suffices) {
            suffices = new HashSet<>();
            suffixMap.put(prefix, suffices);
        }
        suffices.add(suffix);
    }

    /**
     * Returns the count of words in database.
     * 
     * @return
     */
    public int getWordCount() {
        return suffixMap.size();
    }

    /**
     * Prints the contents of database for debugging purpose.
     * 
     * @param printer
     */
    public void printDatabase(TablePrinter printer) {
        int prefixWidth = maxLengthSum(suffixMap.keySet().stream().map(prefix -> prefix.prefix));
        int suffixWidth = maxLengthSum(suffixMap.values().stream());
        printer.setColumnWidths(new int[]{prefixWidth, suffixWidth});
        for (Map.Entry<Prefix, Set<String>> entry: suffixMap.entrySet()) {
            printer.printRow(entry.getKey(), Arrays.deepToString(entry.getValue().toArray()));
        }
    }

    private int maxLengthSum(Stream<? extends Collection<String>> l) {
        return l.mapToInt(wordList -> wordList.stream().mapToInt((word) -> word.length() + 2).sum()).max().getAsInt();
    }

    /**
     * Clear all data previously learned.
     */
    public void reset() {
        suffixMap.clear();
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
        while (0 < --maxLength) {
            Set<String> suffices = suffixMap.get(prefix);
            String word = suffices.stream().skip(rand.nextInt(suffices.size())).findFirst().get();
            if (END_OF_TEXT.equals(word)) {
                break;
            }
            printer.print(word);
            prefix = new Prefix(prefix, word);
        }
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

}
