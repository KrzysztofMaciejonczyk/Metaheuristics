package org.example;

import java.util.ArrayList;
import java.util.Random;

public class FlowShop {
    private Random rand = new Random();
    private ArrayList<ArrayList<Integer>> tasksDurations;
    private int initialTemperature;
    private double coolingRate;
    private double stop;

    public FlowShop(ArrayList<ArrayList<Integer>> tasksDurations, int initialTemperature, double coolingRate, double stop) {
        this.tasksDurations = tasksDurations;
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
        this.stop = stop;
    }
    private int simulatedAnnealing() {
        return 0;
    }
    private ArrayList<Integer> generateSolution(int n) {
        ArrayList<Integer> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            solution.add(i);
        }
        shuffleArray(solution);
        return solution;
    }

    private void shuffleArray(ArrayList<Integer> solution) {
        for (int i = solution.size() - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = solution.get(index);
            solution.set(index, solution.get(i));
            solution.set(i, temp);
//            array[index] = array[i];
//            array[i] = temp;
        }
    }
    private static int calculateMakespan(int[] permutation, int[][] tasksDurations) {
        int n = permutation.length;
        int m = tasksDurations[0].length;

        int[][] completionTimes = new int[n][m];

        completionTimes[0][0] = tasksDurations[permutation[0]][0];
        for (int j = 1; j < m; j++) {
            completionTimes[0][j] = completionTimes[0][j - 1] + tasksDurations[permutation[0]][j];
        }

        for (int i = 1; i < n; i++) {
            completionTimes[i][0] = completionTimes[i - 1][0] + tasksDurations[permutation[i]][0];
            for (int j = 1; j < m; j++) {
                completionTimes[i][j] = Math.max(completionTimes[i - 1][j], completionTimes[i][j - 1]) + tasksDurations[permutation[i]][j];
            }
        }

        return completionTimes[n - 1][m - 1];
    }
}
