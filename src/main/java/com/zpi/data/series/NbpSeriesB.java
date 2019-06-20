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

    public static void analyze(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesB);

        System.out.println("Mediana wynosi: " + Methods.findMedian(valuesCurrency));
        System.out.println("Dominanta wynosi: " + Methods.findMode(valuesCurrency));
        System.out.println("Odchylenie standardowe wynosi: " + Methods.findStandardDeviation(valuesCurrency));
        System.out.println("Współczynnik zmienności wynosi: " + Methods.findCoefficientOfVariation(valuesCurrency));
    }

    public static void analyzeSessions(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = getValuesCurrency(nbpSeriesB);

        System.out.println("Ilość sesji wzrostowych wynosi: " + Methods.findAmountOfGrowthSessions(valuesCurrency));
        System.out.println("Ilość sesji spadkowych wynosi: " + Methods.findAmountOfDownwardSessions(valuesCurrency));
        System.out.println("Ilość sesji bez zmian wynosi: " + Methods.findAmountOfUnchangedSessions(valuesCurrency));
    }

    public static double [] getValuesCurrency(NbpSeriesB nbpSeriesB) {
        double[] valuesCurrency = new double[nbpSeriesB.getRates().size()];

        for (int i = 0; i < nbpSeriesB.getRates().size(); i++) {
            valuesCurrency[i] = nbpSeriesB.getRates().get(i).getMid();
        }

        return valuesCurrency;
    }
}
