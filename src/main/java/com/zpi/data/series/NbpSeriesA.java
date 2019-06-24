package com.zpi.data.series;

import com.zpi.Methods;
import com.zpi.data.rates.Rates;

import java.util.List;

public class NbpSeriesA {
    String table;
    String currency;
    String code;
    List<Rates> rates;

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rates> getRates() {
        return rates;
    }

    public static void analyzeStatisticalMeasures(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesA);

        Methods.showResultsOfStatisticalMeasuresAnalysis(valuesCurrency);
    }

    public static void analyzeSessions(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesA);

        Methods.showResultsOfSessionAnalysis(valuesCurrency);
    }

    public static void compareTwoCurrencies(NbpSeriesA nbpSeriesA1, String currency1, NbpSeriesA nbpSeriesA2, String currency2) {
        double[] valuesCurrency1 = getValuesCurrency(nbpSeriesA1);
        double[] valuesCurrency2 = getValuesCurrency(nbpSeriesA2);

        Methods.showDifferencesBetweenTwoCurrencies(valuesCurrency1, currency1, valuesCurrency2, currency2);
    }

    public static double [] getValuesCurrency(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = new double[nbpSeriesA.getRates().size()];

        for (int i = 0; i < nbpSeriesA.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesA.getRates().get(i).getMid();
        }

        return valuesCurrency;
    }
}

