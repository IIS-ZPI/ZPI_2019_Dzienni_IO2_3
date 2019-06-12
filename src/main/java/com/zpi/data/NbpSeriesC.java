package com.zpi.data;

import com.zpi.Methods;

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
        double[] valuesCurrencyForBid = new double[nbpSeriesC.getRates().size()];
        double[] valuesCurrencyForAsk = new double[nbpSeriesC.getRates().size()];

        for (int i = 0; i < nbpSeriesC.getRates().size(); i++) {
            valuesCurrencyForBid[i] = nbpSeriesC.getRates().get(i).getBid();
            valuesCurrencyForAsk[i] = nbpSeriesC.getRates().get(i).getAsk();
        }

        System.out.println("Cena sprzedaży");
        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrencyForBid));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrencyForBid));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrencyForBid));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrencyForBid));

        System.out.println("Cena kupna");
        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrencyForAsk));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrencyForAsk));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrencyForAsk));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrencyForAsk));
    }
}

