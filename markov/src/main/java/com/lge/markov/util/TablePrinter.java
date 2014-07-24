package com.lge.markov.util;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Prints a table to OutputStream. Before using printRow() to print a row, 
 * it can be configured a column widths using setColumnWidths(int[]).
 * If columnWidth is specified, it prints a cell and pads whitespaces accordingly.
 * If columnWidth is not specified or not positive value, 
 * it just prints the cell value.
 * 
 * <pre>
 * TablePrinter printer = new TablePrinter(System.out);
 * printer.setColumnWidth(new int[]{3,4,-1});
 * printer.printRow(3, 4, -1);
 * printer.printRow("a1", "b2", "ccc");
 * </pre>
 * 
 * will prints as follows:
 * 
 * <pre>
 * 3  4   -1
 * a1 b2  ccc
 * </pre>
 */
public class TablePrinter {

    private PrintWriter pw;
    private int[] columnWidths = new int[] {};

    public TablePrinter(OutputStream outputStream) {
        this.pw = new PrintWriter(outputStream);
    }

    public void setColumnWidths(int[] columnWidths) {
        this.columnWidths = columnWidths;
    }

    public void printRow(Object... cells) {
        int i = 0;
        for (Object cell : cells) {
            if (i < columnWidths.length && columnWidths[i] > 0) {
                pw.printf("%-" + columnWidths[i] + "s", cell);
            } else {
                pw.print(cell);
            }
        }
        pw.println();
        pw.flush();
    }
}
