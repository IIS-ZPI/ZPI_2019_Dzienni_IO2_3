package com.zpi.data.series;

import com.zpi.Methods;
import com.zpi.data.rates.Rates;

import java.util.List;

public class NbpSeriesB {
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

    public static void analyzeStatisticalMeasures(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesB);

        Methods.showResultsOfStatisticalMeasuresAnalysis(valuesCurrency);
    }

    public static void analyzeSessions(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesB);

        Methods.showResultsOfSessionAnalysis(valuesCurrency);
    }

    public static void compareTwoCurrencies(NbpSeriesB nbpSeriesB1, String currency1, NbpSeriesB nbpSeriesB2, String currency2) {
        double[] valuesCurrency1 = getValuesCurrency(nbpSeriesB1);
        double[] valuesCurrency2 = getValuesCurrency(nbpSeriesB2);

        Methods.showDifferencesBetweenTwoCurrencies(valuesCurrency1, currency1, valuesCurrency2, currency2);
    }

    public static double [] getValuesCurrency(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = new double[nbpSeriesB.getRates().size()];

        for (int i = 0; i < nbpSeriesB.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesB.getRates().get(i).getMid();
        }

        return valuesCurrency;
    }
}
