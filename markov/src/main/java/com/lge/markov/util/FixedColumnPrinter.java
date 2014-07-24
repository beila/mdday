package com.lge.markov.util;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Prints every words without overflowing specified by columnWidth.
 * If a line overflows, it prints a newline before printing a word.
 */
public class FixedColumnPrinter implements WordPrinter {
    private PrintWriter pw;
    private int columnWidth;
    private int width;

    public FixedColumnPrinter(OutputStream outputStream, int columnWidth) {
        this.pw = new PrintWriter(outputStream);
        this.columnWidth = columnWidth;
        this.width = 0;
    }

    @Override
    public void print(String word) {
        if (word.length() + width > columnWidth) {
            pw.println();
            width = 0;
        }
        pw.print(word + " ");
        width += word.length() + 1;
        pw.flush();
    }
}
