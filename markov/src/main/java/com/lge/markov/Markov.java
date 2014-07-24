package com.lge.markov;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.lge.markov.util.TablePrinter;

/**
 * Drives a Markov program. It interacts with user via standard input/output. It delegates the
 * command from user to the Chain instance - to construct a database from sample files and - to
 * generate random text according to the statistical prefix-suffix mappings.
 */
public class Markov {
    Chain chain = new Chain();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Markov markov = new Markov();
        markov.loop();
    }

    private void loop() {
        while (true) {
            switch (readMenu()) {
            case 1:
                doLearn();
                break;
            case 2:
                doPrintDatabase();
                break;
            case 3:
                doReset();
                break;
            case 4:
                doGenerateRandomText();
                break;
            default:
                doCleanUp();
                return;
            }
        }
    }

    private int readMenu() {
        showStatus();
        showMenu();
        return readNumberInput("Enter menu [1~5]: ", 1, 5);
    }

    private void showStatus() {
        System.out.println("[[[ Database contains " + chain.getWordCount() + " words. ]]]");
    }

    private void showMenu() {
        System.out.println("1. Learn from sample text");
        System.out.println("2. Print database");
        System.out.println("3. Reset database");
        System.out.println("4. Generate random text");
        System.out.println("5. Exit");
    }

    private int readNumberInput(String prompt, int min, int max) {
        int menu = 0;
        do {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine();
                menu = Integer.valueOf(line);
            } catch (NumberFormatException e) {
                continue;
            }
        } while (menu < min || menu > max);
        return menu;
    }

    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void doLearn() {
        String filename = readStringInput("Enter text filename: ");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            chain.learn(fis);
        } catch (IOException e) {
            System.out.println("Failed to open file : \"" + filename + "\"");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doPrintDatabase() {
        if (chain.getWordCount() > 0) {
            System.out.println("Until now I learned following prefix-suffix map.");
            chain.printDatabase(new TablePrinter(System.out));
        } else {
            System.out.println("I have learned nothing.");
        }
    }

    private void doReset() {
        System.out.println("Clean up database...");
        chain.reset();
    }

    private void doGenerateRandomText() {
        if (chain.getWordCount() > 0) {
            System.out.println("Here is auto-generated text:");
            chain.generate(System.out);
            System.out.println();
        } else {
            System.out.println("Nothing to generate");
        }
    }

    private void doCleanUp() {
        System.out.println("Bye.");
        scanner.close();
    }

}
