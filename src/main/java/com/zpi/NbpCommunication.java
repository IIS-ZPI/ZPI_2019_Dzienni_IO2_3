package com.zpi;

import com.google.gson.Gson;
import com.zpi.data.NbpTableB;
import com.zpi.data.NbpTableC;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NbpCommunication {

    public static void main(String[] args) {
        getNbpTableB();
    }

    private static List<NbpTableB> getNbpTableB()
    {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/B/";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableB> nbpTableB = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableB[].class)));
        System.out.println(nbpTableB.get(0).getRates().get(0).getCode() + " " + nbpTableB.get(0).getRates().get(0).getMid());
        return nbpTableB;
    }

    private static List<NbpTableC> getNbpTableC()
    {
        final String uri = "http://api.nbp.pl/api/exchangerates/tables/C/";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Gson gson = new Gson();
        ArrayList<NbpTableC> nbpTableC = new ArrayList<>(Arrays.asList(gson.fromJson(result, NbpTableC[].class)));
        System.out.println(nbpTableC.get(0).getRates().get(0).getCode() + " " + nbpTableC.get(0).getRates().get(0).getMid());
        return nbpTableC;
    }
}
