package com.zpi.data;

import com.zpi.Methods;

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

    public static void analyze(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = new double[nbpSeriesA.getRates().size()];

        for (int i = 0; i < nbpSeriesA.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesA.getRates().get(i).getMid();
        }

        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrency));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrency));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrency));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrency));
    }
}

