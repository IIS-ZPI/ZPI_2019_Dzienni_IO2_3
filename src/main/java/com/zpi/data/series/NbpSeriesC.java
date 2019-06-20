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

    public static void analyze(NbpSeriesC nbpSeriesC) {
        double[] valuesCurrencyForBid = getValuesCurrencyForBid(nbpSeriesC);
        double[] valuesCurrencyForAsk = getValuesCurrencyForAsk(nbpSeriesC);

        System.out.println("\nCena sprzedaży");
        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrencyForBid));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrencyForBid));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrencyForBid));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrencyForBid));

        System.out.println("\nCena kupna");
        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrencyForAsk));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrencyForAsk));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrencyForAsk));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrencyForAsk));
    }

    public static void analyzeSessions(NbpSeriesC nbpSeriesC) {
        double[] valuesCurrencyForBid = getValuesCurrencyForBid(nbpSeriesC);
        double[] valuesCurrencyForAsk = getValuesCurrencyForAsk(nbpSeriesC);

        System.out.println("\nDla ceny kupna");
        System.out.println("Ilość sesji wzrostowych wynosi: " + Methods.findAmountOfGrowthSessions(valuesCurrencyForBid));
        System.out.println("Ilość sesji spadkowych wynosi: " + Methods.findAmountOfDownwardSessions(valuesCurrencyForBid));
        System.out.println("Ilość sesji bez zmian wynosi: " + Methods.findAmountOfUnchangedSessions(valuesCurrencyForBid));

        System.out.println("\nDla ceny sprzedaży");
        System.out.println("Ilość sesji wzrostowych wynosi: " + Methods.findAmountOfGrowthSessions(valuesCurrencyForAsk));
        System.out.println("Ilość sesji spadkowych wynosi: " + Methods.findAmountOfDownwardSessions(valuesCurrencyForAsk));
        System.out.println("Ilość sesji bez zmian wynosi: " + Methods.findAmountOfUnchangedSessions(valuesCurrencyForAsk));
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
