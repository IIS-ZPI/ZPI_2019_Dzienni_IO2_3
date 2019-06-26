package com.zpi;

import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    public static int findAmountOfGrowthSessions(double[] values) {
        int counter = 0;

        for(int i = 1; i < values.length; i++) {
            if(values[i] - values[i-1] > 0) {
                counter++;
            }
        }

        return counter;
    }

    public static int findAmountOfDownwardSessions(double[] values) {
        int counter = 0;

        for (int i = 1; i < values.length; i++) {
            if (values[i] - values[i - 1] < 0) {
                counter++;
            }
        }

        return counter;
    }

    public static int findAmountOfUnchangedSessions(double[] values){
        int counter = 0;

        for (int i = 1; i < values.length; i++) {
            if (values[i] - values[i - 1] == 0) {
                counter++;
            }
        }

        return counter;
    }

    public static void showDifferencesBetweenTwoCurrencies(double[] values1, String currency1, double[] values2, String currency2) {

        System.out.println("\nAnaliza pokazująca porównanie dwóch walut, ich wzrostów czy spadków względem dnia poprzedniego");

        for(int i = 1; i < values1.length; i++) {
            double diff1 = values1[i] - values1[i-1];
            double diff2 = values2[i] - values2[i-1];

            System.out.println(i + " porównanie");
            showCurrencyAndDifference(currency1, diff1);
            showCurrencyAndDifference(currency2, diff2);

            System.out.println();
        }

    }

    public static List<String> showResultsOfSessionAnalysis(double[] values) {
        String result1 = "Ilość sesji wzrostowych wynosi: " + Methods.findAmountOfGrowthSessions(values);
        String result2 = "Ilość sesji spadkowych wynosi: " + Methods.findAmountOfDownwardSessions(values);
        String result3 = "Ilość sesji bez zmian wynosi: " + Methods.findAmountOfUnchangedSessions(values);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        List<String> results = new ArrayList<>();
        results.add(result1);
        results.add(result2);
        results.add(result3);

        return results;
    }

    public static List<String> showResultsOfStatisticalMeasuresAnalysis(double[] values) {
        String result1 = "Mediana wynosi: " + Methods.findMedian(values);
        String result2 = "Dominanta wynosi: " + Methods.findMode(values);
        String result3 = "Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(values);
        String result4 = "Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(values);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);

        List<String> results = new ArrayList<>();
        results.add(result1);
        results.add(result2);
        results.add(result3);
        results.add(result4);

        return results;
    }

    public static void showCurrencyAndDifference(String currency, double diff) {
        System.out.print("waluta: " + currency + " ");

        if(diff > 0) {
            System.out.println("Wzrost: " + diff);
        }
        else if(diff < 0) {
            System.out.println("Spadek: " + diff);
        }
        else {
            System.out.println("Bez zmian");
        }
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
