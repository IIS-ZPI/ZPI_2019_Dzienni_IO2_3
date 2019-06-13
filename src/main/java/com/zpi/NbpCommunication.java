package com.zpi;

import com.google.gson.Gson;
import com.zpi.data.series.NbpSeriesA;
import com.zpi.data.series.NbpSeriesB;
import com.zpi.data.series.NbpSeriesC;
import com.zpi.data.table.NbpTableA;
import com.zpi.data.table.NbpTableB;
import com.zpi.data.table.NbpTableC;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NbpCommunication {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String chosenCurrency, chosenPeriod;

        System.out.println("Podaj walute z tabeli kursow walut typu A i B dla ktorej przeprowadzic analize: ");
        chosenCurrency = input.nextLine();

        System.out.println("Podaj okres dla ktorego ma byc przeprowadzona analiza: ");
        System.out.println("1. 1 tydzien(7 dni)\n"
                + "2. 2 tygodnie(14 dni)\n"
                + "3. 1 miesiac(30 dni)\n"
                + "4. 1 kwartal(90 dni)\n"
                + "5. pol roku(182 dni)\n"
                + "6. rok(365 dni)");
        chosenPeriod = input.nextLine();
        String amountOfRecords = Methods.changeChosenPeriodIntoDays(chosenPeriod);

        System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu A" +
                " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

        NbpSeriesA.analyze(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));

        System.out.println("\n");

        System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu B" +
                " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

        NbpSeriesB.analyze(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
    }


    public static List<NbpTableB> getNbpTableB() {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/B/";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableB> nbpTableB = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableB[].class)));
        System.out.println(nbpTableB.get(0).getRates().get(0).getCode() + " " + nbpTableB.get(0).getRates().get(0).getMid());
        return nbpTableB;
    }

    public static List<NbpTableA> getNbpTableA() {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableA> nbpTableA = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableA[].class)));
        System.out.println(nbpTableA.get(0).getRates().get(0).getCode() + " " + nbpTableA.get(0).getRates().get(0).getMid());
        return nbpTableA;
    }

    public static List<NbpTableC> getNbpTableC() {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableC> nbpTableC = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableC[].class)));
        System.out.println(nbpTableC.get(0).getRates().get(0).getCode() + " " + nbpTableC.get(0).getRates().get(0).getMid());
        return nbpTableC;
    }

    public static NbpSeriesA getNbpSeriesAForGivenCurrencyFromGivenPeriod(String currency, String days) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currency + "/last/" + days;

        RestTemplate restTemplate = new RestTemplate();
        String resultSeries = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        NbpSeriesA nbpSeriesA = gson.fromJson(resultSeries, NbpSeriesA.class);

        return nbpSeriesA;
    }

    public static NbpSeriesB getNbpSeriesBForGivenCurrencyFromGivenPeriod(String currency, String days) {
        NbpSeriesB nbpSeriesB;
        String url = "http://api.nbp.pl/api/exchangerates/rates/B/" + currency + "/last/" + days;

        RestTemplate restTemplate = new RestTemplate();
        String resultSeries = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        nbpSeriesB = gson.fromJson(resultSeries, NbpSeriesB.class);

        return nbpSeriesB;
    }

    public static NbpSeriesC getNbpSeriesCForGivenCurrencyFromGivenPeriod(String currency, String days) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/C/" + currency + "/last/" + days;

        RestTemplate restTemplate = new RestTemplate();
        String resultSeries = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        NbpSeriesC nbpSeriesC = gson.fromJson(resultSeries, NbpSeriesC.class);

        return nbpSeriesC;
    }
}
