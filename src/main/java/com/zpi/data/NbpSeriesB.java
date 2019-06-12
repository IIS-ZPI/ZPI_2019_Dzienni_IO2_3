package com.zpi.data;

import com.zpi.Methods;

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

    public static void analyze(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = new double[nbpSeriesB.getRates().size()];

        for (int i = 0; i < nbpSeriesB.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesB.getRates().get(i).getMid();
        }

        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrency));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrency));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrency));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrency));
    }
}