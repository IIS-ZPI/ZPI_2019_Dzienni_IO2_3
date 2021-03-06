package com.zpi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
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
    String chosenCurrency, secondChosenCurrency, chosenPeriod, chosenTable, chosenAnalyze;
    Set<String> currency = new HashSet<>();
    List<String> listToSave = new ArrayList<>();

    public Menu() {
        try {
            while (true) {
                getAnalyzeTypeFromUser();
                if (!isCorrectAnalyzeInput(chosenAnalyze)) {
                    if (repeatAttempt()) {
                        continue;
                    } else {
                        break;
                    }
                }


                getTableFromUser();
                if (!isCorrectTableLetterInput(chosenTable)) {
                    if (repeatAttempt()) {
                        continue;
                    } else {
                        break;
                    }
                }

                boolean loadSuccesful = loadPossibleCurrency();
                if(!loadSuccesful) {
                    break;
                }

                getFirstCurrencyFromUser();
                if (!isCorrectCurrencyInput(chosenCurrency)) {
                    if (repeatAttempt()) {
                        continue;
                    } else {
                        break;
                    }
                }

                if (chosenAnalyze.equals("3")) {
                    getSecondCurrencyFromUser();
                    if (!isCorrectCurrencyInput(secondChosenCurrency) && !secondChosenCurrency.equals(chosenCurrency)) {
                        if (repeatAttempt()) {
                            continue;
                        } else {
                            break;
                        }
                    }
                }

                getPeriodFromUser();

                if (!isCorrectPeriodInput(chosenPeriod)) {
                    if (repeatAttempt()) {
                        continue;
                    } else {
                        break;
                    }
                }

                String amountOfRecords = Methods.changeChosenPeriodIntoDays(chosenPeriod);

                try {
                    startAnalyze(amountOfRecords);
                } catch (Exception e) {
                    System.out.println("Wystapil blad podczas polaczenia z api.");
                    if (exit(false)) {
                        new Menu();
                    } else {
                        return;
                    }
                    break;
                }
                if (exit(true)) {
                    continue;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Wystapil niespodziewany blad.");
            if (exit(false)) {
                new Menu();
            } else {
                return;
            }
        }
    }

    private void getSecondCurrencyFromUser() {
        System.out.println("Podaj kod drugiej waluty z tabeli kursow walut dla ktorej przeprowadzic analize: ");
        System.out.println("Mozliwe:");
        System.out.println(currency);
        secondChosenCurrency = input.nextLine();
    }

    private void getFirstCurrencyFromUser() {
        System.out.println("Podaj kod waluty z tabeli kursow walut dla ktorej przeprowadzic analize: ");
        System.out.println("Mozliwe:");
        System.out.println(currency);
        chosenCurrency = input.nextLine();
    }

    private void getTableFromUser() {
        System.out.println("Podaj dla jakiej tabeli przeprowadzic analize:");
        System.out.println("Mozliwe: A, B, C.");
        chosenTable = input.nextLine();
    }

    private void getAnalyzeTypeFromUser() {
        System.out.println("Podaj typ anlizy, ktory chcesz wykonac:");
        System.out.println("1. Ilosc sesji wzrostowych/spadkowych/bez zmian.\n"
                + "2. Miary statystyczne\n"
                + "3. Rozklad zmian walutowych\n");
        chosenAnalyze = input.nextLine();
    }

    private void getPeriodFromUser() {
        System.out.println("Podaj okres dla ktorego ma byc przeprowadzona analiza: ");
        System.out.println("1. 1 tydzien(7 dni)\n"
                + "2. 2 tygodnie(14 dni)\n"
                + "3. 1 miesiac(30 dni)\n"
                + "4. 1 kwartal(90 dni)\n"
                + "5. pol roku(182 dni)\n"
                + "6. rok(365 dni)");
        System.out.println("Wybierz jedna z mozliwosci. (1-6)");
        chosenPeriod = input.nextLine();
    }

    private void startAnalyze(String amountOfRecords) {
        if (chosenTable.equals("A")) {
            performTableAAnalyze(amountOfRecords);
        }

        if (chosenTable.equals("B")) {
            performTableBAnalyze(amountOfRecords);
        }

        if (chosenTable.equals("C")) {
            performTableCAnalyze(amountOfRecords);
        }
    }

    private void performTableCAnalyze(String amountOfRecords) {
        if (chosenAnalyze.equals("1")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu C"
                    + " dla waluty "
                    + chosenCurrency
                    + " w okreslonym okresie. Ilosc rekordow: "
                    + amountOfRecords);

            listToSave = NbpSeriesC.analyzeSessions(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
        } else if (chosenAnalyze.equals("2")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu C"
                    + " dla waluty "
                    + chosenCurrency
                    + " w okreslonym okresie. Ilosc rekordow: "
                    + amountOfRecords);

            listToSave = NbpSeriesC.analyzeStatisticalMeasures(
                    getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
        } else if (chosenAnalyze.equals("3")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu C"
                    + " dla walut "
                    + chosenCurrency
                    + " i "
                    + secondChosenCurrency
                    + " w okreslonym okresie. Ilosc rekordow: "
                    + amountOfRecords);
            NbpSeriesC.compareTwoCurrencies(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords),
                    chosenCurrency, getNbpSeriesCForGivenCurrencyFromGivenPeriod(secondChosenCurrency, amountOfRecords),
                    secondChosenCurrency);
        }
        System.out.println("\n");
    }

    private void performTableBAnalyze(String amountOfRecords) {
        if (chosenAnalyze.equals("1")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu B" +
                    " dla waluty " + chosenCurrency + " w okreslonym okresie. Ilosc rekordow: " + amountOfRecords);

            listToSave = NbpSeriesB.analyzeSessions(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
        } else if (chosenAnalyze.equals("2")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu B" +
                    " dla waluty " + chosenCurrency + " w okreslonym okresie. Ilosc rekordow: " + amountOfRecords);

            listToSave = NbpSeriesB.analyzeStatisticalMeasures(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
        } else if (chosenAnalyze.equals("3")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu B" +
                    " dla walut " + chosenCurrency + " i " + secondChosenCurrency + " w okreslonym okresie. Ilosc rekordow: " + amountOfRecords);
            NbpSeriesB.compareTwoCurrencies(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                    getNbpSeriesBForGivenCurrencyFromGivenPeriod(secondChosenCurrency, amountOfRecords), secondChosenCurrency);
        }
        System.out.println("\n");
    }

    private void performTableAAnalyze(String amountOfRecords) {
        if (chosenAnalyze.equals("1")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu A" +
                    " dla waluty " + chosenCurrency + " w okreslonym okresie. Ilosc rekordow: " + amountOfRecords);

            listToSave = NbpSeriesA.analyzeSessions(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
        } else if (chosenAnalyze.equals("2")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu A" +
                    " dla waluty " + chosenCurrency + " w okreslonym okresie. Ilosc rekordow: " + amountOfRecords);

            listToSave = NbpSeriesA.analyzeStatisticalMeasures(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
        } else if (chosenAnalyze.equals("3")) {
            System.out.println("Analiza bedzie przeprowadzona dla tabeli kursow walut typu A" +
                    " dla walut " + chosenCurrency + " i " + secondChosenCurrency + " w okreslonym okresie. Ilosc rekordow: " + amountOfRecords);
            NbpSeriesA.compareTwoCurrencies(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                    getNbpSeriesAForGivenCurrencyFromGivenPeriod(secondChosenCurrency, amountOfRecords), secondChosenCurrency);
        }
        System.out.println("\n");
    }

    private Boolean repeatAttempt() {
        while (true) {
            System.out.println("Podano niepoprawna wartosc. Powtorzyc? T/N");
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

    public void saveResults() {
        try (FileWriter fw = new FileWriter("results.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Analiza typ: " + chosenAnalyze + ", tabela: " + chosenTable + ", okres: "
                    + chosenPeriod + ", waluta: " + chosenCurrency);
            for (int i = 0; i < listToSave.size(); i++) {
                out.println(listToSave.get(i));
            }
            out.println("------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Blad zapisu");
        }
    }

    private boolean exit(boolean askForSave) {

        if (!chosenAnalyze.equals("3") && askForSave) {
            System.out.println("Czy chcesz zapisac wyniki ostatniej analizy? T/N");
            String answer = input.nextLine();

            if (answer.toLowerCase().equals("t")) {
                saveResults();
            }
        }

        while (true) {
            System.out.println("Czy chcesz ponownie wykonac operacje? T/N");
            String result = input.nextLine();

            if (result.toLowerCase().equals("t")) {
                return true;
            } else if (result.toLowerCase().equals("n")) {
                return false;
            } else {
                continue;
            }
        }
    }


    private Boolean isCorrectCurrencyInput(String input) {
        if (currency == null) {
            return false;
        }

        if (currency.contains(input)) {
            return true;
        }

        return false;
    }

    private boolean loadPossibleCurrency() {
        try {
            if (chosenTable.equals("A")) {
                List<NbpTableA> nbpTableList;
                nbpTableList = NbpCommunication.getNbpTableA();
                for (int i = 0; i < nbpTableList.size(); i++) {
                    for (int j = 0; j < nbpTableList.get(i).getRates().size(); j++) {
                        currency.add(nbpTableList.get(i).getRates().get(j).getCode());
                    }
                }
                return true;
            } else if (chosenTable.equals("B")) {
                List<NbpTableB> nbpTableList;
                nbpTableList = NbpCommunication.getNbpTableB();
                for (int i = 0; i < nbpTableList.size(); i++) {
                    for (int j = 0; j < nbpTableList.get(i).getRates().size(); j++) {
                        currency.add(nbpTableList.get(i).getRates().get(j).getCode());
                    }
                }
                return true;
            } else if (chosenTable.equals("C")) {
                List<NbpTableC> nbpTableList;
                nbpTableList = NbpCommunication.getNbpTableC();
                for (int i = 0; i < nbpTableList.size(); i++) {
                    for (int j = 0; j < nbpTableList.get(i).getRates().size(); j++) {
                        currency.add(nbpTableList.get(i).getRates().get(j).getCode());
                    }
                }
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Wystapil blad podczas polaczenia z api.");
            if (exit(false)) {
                new Menu();
            } else {
                return false;
            }
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
        if (input.toLowerCase().matches("[abc]")) {
            return true;
        }
        return false;
    }

    private Boolean isCorrectAnalyzeInput(String input) {
        if (input.matches("[123]")) {
            return true;
        }
        return false;
    }

}
