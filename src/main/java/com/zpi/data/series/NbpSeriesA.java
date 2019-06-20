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

    public static void analyze(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesA);

        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrency));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrency));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrency));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrency));
    }

    public static void analyzeSessions(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesA);

        System.out.println("Ilość sesji wzrostowych wynosi: " + Methods.findAmountOfGrowthSessions(valuesCurrency));
        System.out.println("Ilość sesji spadkowych wynosi: " + Methods.findAmountOfDownwardSessions(valuesCurrency));
        System.out.println("Ilość sesji bez zmian wynosi: " + Methods.findAmountOfUnchangedSessions(valuesCurrency));
    }

    public static double [] getValuesCurrency(NbpSeriesA nbpSeriesA) {
        double[] valuesCurrency = new double[nbpSeriesA.getRates().size()];

        for (int i = 0; i < nbpSeriesA.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesA.getRates().get(i).getMid();
        }

        return valuesCurrency;
    }
}

