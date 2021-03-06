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
        Menu menu = new Menu();
    }

    public static List<NbpTableA> getNbpTableA() {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableA> nbpTableA = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableA[].class)));

        return nbpTableA;
    }

    public static List<NbpTableB> getNbpTableB() {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/B/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableB> nbpTableB = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableB[].class)));

        return nbpTableB;
    }

    public static List<NbpTableC> getNbpTableC() {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/C/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableC> nbpTableC = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableC[].class)));

        return nbpTableC;
    }

    public static NbpSeriesA getNbpSeriesAForGivenCurrencyFromGivenPeriod(String currency, String days) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currency + "/last/" + days + "/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String resultSeries = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        NbpSeriesA nbpSeriesA = gson.fromJson(resultSeries, NbpSeriesA.class);

        return nbpSeriesA;
    }

    public static NbpSeriesB getNbpSeriesBForGivenCurrencyFromGivenPeriod(String currency, String days) {
        NbpSeriesB nbpSeriesB;
        String url = "http://api.nbp.pl/api/exchangerates/rates/B/" + currency + "/last/" + days + "/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String resultSeries = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        nbpSeriesB = gson.fromJson(resultSeries, NbpSeriesB.class);

        return nbpSeriesB;
    }

    public static NbpSeriesC getNbpSeriesCForGivenCurrencyFromGivenPeriod(String currency, String days) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/C/" + currency + "/last/" + days + "/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String resultSeries = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        NbpSeriesC nbpSeriesC = gson.fromJson(resultSeries, NbpSeriesC.class);

        return nbpSeriesC;
    }
}

/**
 * For the brave souls who get this far: You are the chosen ones,
 * the valiant knights of programming who toil away, without rest,
 * fixing our most awful code. To you, true saviors, kings of men,
 * I say this: never gonna give you up, never gonna let you down,
 * never gonna run around and desert you. Never gonna make you cry,
 * never gonna say goodbye. Never gonna tell a lie and hurt you.
 */