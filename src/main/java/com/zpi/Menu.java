package com.zpi;

import java.util.*;

import com.zpi.data.series.NbpSeriesA;
import com.zpi.data.series.NbpSeriesB;
import com.zpi.data.series.NbpSeriesC;
import com.zpi.data.table.NbpTableA;
import com.zpi.data.table.NbpTableB;
import com.zpi.data.table.NbpTableC;

import static com.zpi.NbpCommunication.getNbpSeriesAForGivenCurrencyFromGivenPeriod;
import static com.zpi.NbpCommunication.getNbpSeriesBForGivenCurrencyFromGivenPeriod;
import static com.zpi.NbpCommunication.getNbpSeriesCForGivenCurrencyFromGivenPeriod;

public class Menu {
    Scanner input = new Scanner(System.in);
    String chosenCurrency, chosenPeriod, chosenTable;

    public Menu() {
        while (true) {

            System.out.println("Podaj dla jakiej tabeli przeprowadzic analize:");
            System.out.println("Możliwe: A, B, C.");
            chosenTable = input.nextLine();
            if (!isCorrectTableLetterInput(chosenTable)) {
                if (repeatAttempt()) {
                    continue;
                } else {
                    break;
                }
            }

            System.out.println("Podaj kod waluty z tabeli kursow walut dla ktorej przeprowadzic analize: ");
            System.out.println("Przykład: USD, EUR, AUD.");
            chosenCurrency = input.nextLine();
            if (!isCorrectCurrencyInput(chosenCurrency)) {
                if (repeatAttempt()) {
                    continue;
                } else {
                    break;
                }
            }

            System.out.println("Podaj okres dla którego ma byc przeprowadzona analiza: ");
            System.out.println("1. 1 tydzien(7 dni)\n"
                    + "2. 2 tygodnie(14 dni)\n"
                    + "3. 1 miesiac(30 dni)\n"
                    + "4. 1 kwartal(90 dni)\n"
                    + "5. pol roku(182 dni)\n"
                    + "6. rok(365 dni)");
            System.out.println("Wybierz jedną z możliwości. (1-6)");
            chosenPeriod = input.nextLine();

            if (!isCorrectPeriodInput(chosenPeriod)) {
                if (repeatAttempt()) {
                    continue;
                } else {
                    break;
                }
            }

            String amountOfRecords = Methods.changeChosenPeriodIntoDays(chosenPeriod);

            if (chosenTable.equals("A")) {
                System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu A" +
                        " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                NbpSeriesA.analyzeStatisticalMeasures(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));

                //test funkcji analyzeSessions, trzeba zrobić do tego opcje w menu
                NbpSeriesA.analyzeSessions(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                //test funkcji compareTwoCurrencies, trzeba zrobić do tego opcje w menu
                NbpSeriesA.compareTwoCurrencies(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                        getNbpSeriesAForGivenCurrencyFromGivenPeriod("EUR", amountOfRecords), "EUR");

                System.out.println("\n");
            }

            if (chosenTable.equals("B")) {
                System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu B" +
                        " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                NbpSeriesB.analyzeStatisticalMeasures(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                System.out.println("\n");
            }

            if (chosenTable.equals("C")) {
                System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu C" +
                        " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                NbpSeriesC.analyzeStatisticalMeasures(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));

                NbpSeriesC.compareTwoCurrencies(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                        getNbpSeriesCForGivenCurrencyFromGivenPeriod("EUR", amountOfRecords), "EUR");

                System.out.println("\n");


            }
            if (exit()) {
                continue;
            } else {
                break;
            }
        }
    }

    private Boolean repeatAttempt() {
        while (true) {
            System.out.println("Podano niepoprawną wartość. Powtórzyć? T/N");
            String result = input.nextLine();
            if (result.equals("T")) {
                return true;
            } else if (result.equals("N")) {
                return false;
            } else {
                continue;
            }
        }
    }

    private boolean exit() {
        while (true) {
            System.out.println("Czy chcesz ponownie wykonać operację? T/N");
            String result = input.nextLine();

            if (result.equals("T")) {
                return true;
            } else if (result.equals("N")) {
                return false;
            } else {
                continue;
            }
        }
    }


    private Boolean isCorrectCurrencyInput(String input) {
        Set<String> currency = new HashSet<>();

        if(chosenTable.equals("A")) {
            List<NbpTableA> nbpTableList;
            nbpTableList = NbpCommunication.getNbpTableA();
            for (int i = 0; i < nbpTableList.size(); i++) {
                for (int j = 0; j < nbpTableList.get(i).getRates().size(); j++) {
                    currency.add(nbpTableList.get(i).getRates().get(j).getCode());
                }
            }
        } else if(chosenTable.equals("B")) {
            List<NbpTableB> nbpTableList;
            nbpTableList = NbpCommunication.getNbpTableB();
            for (int i = 0; i < nbpTableList.size(); i++) {
                for (int j = 0; j < nbpTableList.get(i).getRates().size(); j++) {
                    currency.add(nbpTableList.get(i).getRates().get(j).getCode());
                }
            }
        } else if(chosenTable.equals("C")) {
            List<NbpTableC> nbpTableList;
            nbpTableList = NbpCommunication.getNbpTableC();
            for (int i = 0; i < nbpTableList.size(); i++) {
                for (int j = 0; j < nbpTableList.get(i).getRates().size(); j++) {
                    currency.add(nbpTableList.get(i).getRates().get(j).getCode());
                }
            }
        } else {
            return false;
        }

        if (currency.contains(input)) {
            return true;
        }

        return false;
    }

    private Boolean isCorrectPeriodInput(String input) {
        if (input.matches("[1-6]")) {
            return true;
        }
        return false;
    }

    private Boolean isCorrectTableLetterInput(String input) {
        if (input.matches("[ABC]")) {
            return true;
        }
        return false;
    }

}