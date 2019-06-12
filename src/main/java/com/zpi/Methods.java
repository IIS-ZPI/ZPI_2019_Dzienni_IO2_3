package com.zpi;

import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.text.DecimalFormat;

public class Methods {

    public static double roundValue(Double value) {
        String newValue = new DecimalFormat("##.####").format(value);
        newValue = newValue.replace(",", ".");

        return Double.parseDouble(newValue);
    }

    public static double findMedian(double[] values) {
        Median median = new Median();

        return roundValue(median.evaluate(values));
    }

    public static double findMode(double[] values) {
        double dominanta = 0;
        double maks = 0;
        int counter = 0;

        for (int i = 0; i < values.length; i++) {
            counter = 0;
            for (int k = 0; k < values.length; k++) {
                if (values[i] == values[k]) {
                    counter++;
                    if (counter > maks) {
                        dominanta = values[i];
                        maks = counter;
                    }
                }

            }
        }
        return roundValue(dominanta);
    }

    public static double findMean(double[] values) {
        double sum = 0;
        int n = values.length;

        for (int i = 0; i < n; i++) {
            sum += values[i];
        }

        return roundValue(sum / (double) n);
    }

    public static double findStandardDeviation(double[] values) {
        int n = values.length;
        double sqDiff = 0;
        double mean = findMean(values);

        for (int i = 0; i < n; i++) {
            sqDiff += (values[i] - mean) * (values[i] - mean);
        }

        return roundValue(Math.sqrt(sqDiff / n));
    }

    public static double findCoefficientOfVariation(double[] values) {
        return roundValue(findStandardDeviation(values) / findMean(values)*100);
    }

    public static String changeChosenPeriodIntoDays(String chosenPeriod) {
        String days;

        switch(chosenPeriod) {
            case "1":
                days = "5";
                break;
            case "2":
                days = "10";
                break;
            case "3":
                days = "26";
                break;
            case "4":
                days = "78";
                break;
            case "5":
                days = "127";
                break;
            case "6":
                days = "255";
                break;
            default:
                days = "1";
        }
        return days;
    }
}
