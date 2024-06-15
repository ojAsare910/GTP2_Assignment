/*
Created by: Justice Asare Owusu
June 14 2024
 */

package com.justiceowusu;

import java.util.Scanner;

public class PeakColumnsAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] peakMatrix = getMatrixInput(scanner);

        getPeakColumns(peakMatrix);

        scanner.close();
    }

    private static int[][] getMatrixInput(Scanner scanner) {
        int numRows = 0, numCols = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter dimensions (rows, columns): ");
            String input = scanner.nextLine().trim();
            String[] dimensions = input.split("\\s*,\\s*");// trim the spaces before and after around the comma

            if (dimensions.length == 2) {
                try {
                    numRows = Integer.parseInt(dimensions[0]);
                    numCols = Integer.parseInt(dimensions[1]);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entered a non-numeric value. Please enter two integers separated by a comma.");
                }
            } else {
                System.out.println("Invalid dimensions format. Please enter two integers separated by a comma.");
            }
        }

        int[][] matrix = new int[numRows][numCols];
        System.out.println("Enter the matrix elements row by row:");

        for (int i = 0; i < numRows; i++) {
            matrix[i] = getValidRow(scanner, numCols);
        }
        return matrix;
    }

    private static int[] getValidRow(Scanner scanner, int expectedCols) {
        int[] row = new int[expectedCols];
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter " + expectedCols + " integers for the row separated by spaces: ");
            String input = scanner.nextLine().trim();
            String[] elements = input.split("\\s+"); // split by one or more spaces

            if (elements.length == expectedCols) {
                try {
                    for (int j = 0; j < expectedCols; j++) {
                        row[j] = Integer.parseInt(elements[j]);
                    }
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entered a non-numeric value. Please enter " + expectedCols + " integers separated by spaces.");
                }
            } else {
                System.out.println("Number of elements should be " + expectedCols + ".");
            }
        }
        return row;
    }

    private static void getPeakColumns(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            // Maximum element in the current row
            int rowMax = getMaximumRow(matrix[i]);

            // Check all elements in the row that are equal to rowMax
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == rowMax) {
                    if (isMinInCol(matrix, j, rowMax)) {
                        printPeakColumns(i, j, rowMax);
                    }
                }
            }
        }
    }

    // Method to find the maximum element in a row
    private static int getMaximumRow(int[] row) {
        int rowMax = row[0];
        for (int i = 1; i < row.length; i++) {
            if (row[i] > rowMax) {
                rowMax = row[i];
            }
        }
        return rowMax;
    }

    // Method to check if a value is the minimum in a column
    private static boolean isMinInCol(int[][] matrix, int col, int value) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] < value) {
                return false;
            }
        }
        return true;
    }

    private static void printPeakColumns(int row, int col, int value) {
        System.out.println("(" + (row + 1) + "," + (col + 1) + ") = " + value);
    }
}