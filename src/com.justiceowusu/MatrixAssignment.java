/*
Justice Asare Owusu
June 14 2024
 */
package com.justiceowusu;

import java.util.Scanner;

public class MatrixAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] firstMatrix = getMatrixInput(scanner, "A");
        int[][] secondMatrix = getMatrixInput(scanner, "B");

        if (firstMatrix[0].length != secondMatrix.length) { // Validate if multiplication is possible
            System.out.println("Number of columns in A must be equal to number of rows in B.");
        } else {
            int[][] product = multiplyMatrices(firstMatrix, secondMatrix);  // Multiply matrices
            printMatrix(product); // Print the result
        }
        scanner.close();
    }

    private static int[][] getMatrixInput(Scanner scanner, String matrixName) {
        int numRows = 0, numCols = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter dimensions (rows, columns) for Matrix " + matrixName + ": ");
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
        System.out.println("Enter elements of Matrix " + matrixName + " row by row:");

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

    private static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB  = B[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println();
        System.out.println("Matrix " + "C" + ":");
        for (int[] row : matrix) {
            System.out.print("| ");
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println("|");
        }
    }
}
