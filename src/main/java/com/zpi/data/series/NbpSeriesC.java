package com.zpi.data.series;

import com.zpi.Methods;
import com.zpi.data.rates.RatesForSeriesC;

import java.util.List;

public class NbpSeriesC {
    String table;
    String no;
    String tradingDate;
    String effectiveDate;
    List<RatesForSeriesC> rates;

    public String getTable() {
        return table;
    }

    public List<RatesForSeriesC> getRates() {
        return rates;
    }

    public static void analyzeStatisticalMeasures(NbpSeriesC nbpSeriesC) {
        double[] valuesCurrencyForBid = getValuesCurrencyForBid(nbpSeriesC);
        double[] valuesCurrencyForAsk = getValuesCurrencyForAsk(nbpSeriesC);

        System.out.println("\nCena sprzedaży");
        Methods.showResultsOfStatisticalMeasuresAnalysis(valuesCurrencyForBid);

        System.out.println("\nCena kupna");
        Methods.showResultsOfStatisticalMeasuresAnalysis(valuesCurrencyForAsk);
    }

    public static void analyzeSessions(NbpSeriesC nbpSeriesC) {
        double[] valuesCurrencyForBid = getValuesCurrencyForBid(nbpSeriesC);
        double[] valuesCurrencyForAsk = getValuesCurrencyForAsk(nbpSeriesC);

        System.out.println("\nDla ceny kupna");
        Methods.showResultsOfSessionAnalysis(valuesCurrencyForBid);

        System.out.println("\nDla ceny sprzedaży");
        Methods.showResultsOfSessionAnalysis(valuesCurrencyForAsk);
    }

    public static void compareTwoCurrencies(NbpSeriesC nbpSeriesC1, String currency1, NbpSeriesC nbpSeriesC2, String currency2) {
        double[] valuesCurrencyForBid1 = getValuesCurrencyForBid(nbpSeriesC1);
        double[] valuesCurrencyForBid2 = getValuesCurrencyForBid(nbpSeriesC2);

        double[] valuesCurrencyForAsk1 = getValuesCurrencyForAsk(nbpSeriesC1);
        double[] valuesCurrencyForAsk2 = getValuesCurrencyForAsk(nbpSeriesC2);

        System.out.println("\nDla ceny kupna");
        Methods.showDifferencesBetweenTwoCurrencies(valuesCurrencyForBid1, currency1, valuesCurrencyForBid2, currency2);

        System.out.println("\nDla ceny sprzedaży");
        Methods.showDifferencesBetweenTwoCurrencies(valuesCurrencyForAsk1, currency1, valuesCurrencyForAsk2, currency2);

    }

    public static double [] getValuesCurrencyForBid(NbpSeriesC nbpSeriesC) {
        double[] valuesCurrency = new double[nbpSeriesC.getRates().size()];

        for (int i = 0; i < nbpSeriesC.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesC.getRates().get(i).getBid();
        }

        return valuesCurrency;
    }

    public static double [] getValuesCurrencyForAsk(NbpSeriesC nbpSeriesC) {
        double[] valuesCurrency = new double[nbpSeriesC.getRates().size()];

        for (int i = 0; i < nbpSeriesC.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesC.getRates().get(i).getAsk();
        }

        return valuesCurrency;
    }

}
