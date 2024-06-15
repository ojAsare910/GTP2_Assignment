/*
Created by: Justice Asare Owusu
Date: 12th June 2024
 */

package com.justiceowusu;

import java.util.Scanner;

public class GradeStatistics {

    private static final String[] RANGE_LABELS = {"0 - 20", "21 - 40", "41 - 60", "61 - 80", "81 - 100"};

    public static void main(String[] args) {
        printStatsInfo();
    }

    private static int[] initialScoresArray(int[] numbers) {
        int[] initialScores = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            initialScores[i] = numbers[i];
        }
        return initialScores;
    }

    private static int[] getScoresArray() {
        Scanner scanner = new Scanner(System.in);

        int[] scores = {};
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the scores with spaces: ");
            String input = scanner.nextLine();
            String[] arrangedScores = input.split(" ");
            scores = new int[arrangedScores.length];
            try {
                for (int i = 0; i < arrangedScores.length; i++) {
                    scores[i] = Integer.parseInt(arrangedScores[i]);
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Entered a non-numeric value. Please enter integers separated by spaces.");
            }
        }
        scanner.close();
        return scores;

    }

    private static int getMax(int[] numbers) {
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static int getMin(int[] numbers) {
        int min = numbers[0];
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static int[] calculateScoreDistribution(int[] scores) {
        int[] stats =  new int[5];
        for (int grade : scores) {
            if (grade  >= 0 && grade <= 20) {
                stats[0]++;
            } else if (grade >= 20 && grade <= 40) {
                stats[1]++;
            } else if (grade >= 40 && grade <= 60) {
                stats[2]++;
            } else if (grade >= 60 && grade <= 80) {
                stats[3]++;
            } else if (grade >= 80 && grade <= 100) {
                stats[4]++;
            }
        }
        return stats;
    }

    private static void printStatsInfo() {
        int[] scores = getScoresArray();
        int[] scoresList = initialScoresArray(scores);

        int sum = 0;
        for (int score : scoresList) {
            sum += score;
        }

        int min = getMin(scoresList);
        int max = getMax(scoresList);
        double average = (double) sum / scoresList.length;

        System.out.println();
        System.out.println("Values:");
        System.out.println();
        System.out.println("The max grade is: " + max);
        System.out.println("The min grade is: " + min);
        System.out.println("The average grade is: " + average);
        System.out.println();
        System.out.println();
        System.out.println();

        int[] stats = calculateScoreDistribution(scoresList);
        printGraphInfo(stats);
    }

    private static void printGraphInfo(int[] stats) {
        int max = getMax(stats);

        System.out.println("Graph:");
        System.out.println();
        System.out.println();

        for (int i = max; i > 0; i--) {
            System.out.printf("%2d  > ", i);
            for (int num : stats) {
                if (num >= i) {
                    System.out.print(" #######   ");
                } else {
                    System.out.print("           ");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < RANGE_LABELS.length; i++) {
            if (i == 0) {
                System.out.print("    +----------");
            } else {
                System.out.print("+----------");
            }
        }
        System.out.println("+");

        for (int i = 0; i < RANGE_LABELS.length; i++) {
            if (i == 0) {
                System.out.print("       " + RANGE_LABELS[i]);
            } else {
                System.out.print("    " + RANGE_LABELS[i]);
            }
        }
        System.out.println();
    }

}


