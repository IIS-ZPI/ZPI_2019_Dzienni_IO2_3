package com.zpi;

import com.zpi.data.NbpSeriesA;

import java.util.Scanner;

import com.google.gson.*;

import static com.zpi.NbpCommunication.getNbpSeriesAForGivenCurrencyFromGivenPeriod;

public class Menu {

    private Scanner input = new Scanner(System.in);

    private String chosenCurrency, chosenPeriod;

    public Menu() {
        while(true) {

            System.out.println("Podaj kod waluty z tabeli kursow walut typu A dla ktorej przeprowadzic analize: ");
            System.out.println("Przykład: USD, EUR, AUD");
            chosenCurrency = input.nextLine();
            if(!isCorrectCurrencyInput(chosenCurrency)){
                if(repeatAttempt()) {
                    continue;
                } else {
                    break;
                }
            }

            System.out.println("Podaj okres dla ktorego ma byc przeprowadzona analiza: ");
            System.out.println("1. 1 tydzien(7 dni)\n"
                    + "2. 2 tygodnie(14 dni)\n"
                    + "3. 1 miesiac(30 dni)\n"
                    + "4. 1 kwartal(90 dni)\n"
                    + "5. pol roku(182 dni)\n"
                    + "6. rok(365 dni)");
            chosenPeriod = input.nextLine();
            String daysPeriod = Methods.changeChosenPeriodIntoDays(chosenPeriod);

            System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu A" +
                    " dla waluty " + chosenCurrency + " w ostatnim okresie o długości dni: " + daysPeriod);

            NbpSeriesA.analyze(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, daysPeriod));
        }
    }

    private Boolean repeatAttempt() {
        while(true) {
            System.out.println("Podano niepoprawną wartość. Powtórzyć? T/N");
            String result = input.nextLine();

            if(result.equals("T")) {
                return true;
            } else if(result.equals("N")){
                return false;
            } else {
                continue;
            }
        }
    }

    private Boolean isCorrectCurrencyInput(String input) {
        //dodać pobieranie tabeli exchangerate A i obsługę konkretnej waluty.

        JsonObject jsonObject = new JsonObject(jsonString);
        JsonArray friends = jsonObject.getAsJsonArray("data");
        for (int index=0; index<friends.length(); ++index){
            JsonObject currentFriend = friends.getAsJsonArray(index);
            String id = currentFriend.get("data").getAsString();
        }
        return false;
    }
}
