package entity;

import java.util.Arrays;
import java.util.Comparator;

public class Summary implements Comparable<Summary> {

    private final double[] sumArray;
    private final int[] countArray;

    public Summary() {
        countArray = new int[5];
        // 0 - no of loans
        // 1 - no of locked loans
        // 2 - no of savings
        // 3 - no of accounts
        // 4 - no of transactions last 7 days
        sumArray = new double[4];
        // 0 - loan balance
        // 1 - loan balance x rate
        // 2 - deposit balance
        // 3 - deposit balance x rate
    }

    public void addToCount(int index, int value) {
        countArray[index] += value;
    }

    public void addToSum(int index, double value) {
        sumArray[index] += value;
    }

    public int getCount(int index) {
        return countArray[index];
    }

    public double getSum(int index) {
        return sumArray[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(sumArray) + ")\n" +
                Arrays.toString(countArray);
    }

    @Override
    public int compareTo(Summary o) {

        // 70% from loan x rate + 20% from deposit balance + 10% from loan balance
        double amount = 0.7 * this.getSum(1) + 0.2 * this.getSum(2) + 0.1 * this.getSum(0);
        double otherAmount = 0.7 * o.getSum(1) + 0.2 * o.getSum(2) + 0.1 * o.getSum(0);

        return Double.compare(otherAmount, amount);
    }
}
