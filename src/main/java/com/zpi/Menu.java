package com.zpi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        while (true) {
            System.out.println("Podaj typ anlizy, który chcesz wykonać:");
            System.out.println("1. Ilość sesji wzrostowych/spadkowych/bez zmian.\n"
                    + "2. Miary statystyczne\n"
                    + "3. Rozkład zmian walutowych\n");
            chosenAnalyze = input.nextLine();
            if (!isCorrectAnalyzeInput(chosenAnalyze)) {
                if (repeatAttempt()) {
                    continue;
                } else {
                    break;
                }
            }

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

            loadPossibleCurrency();

            System.out.println("Podaj kod waluty z tabeli kursow walut dla ktorej przeprowadzic analize: ");
            System.out.println("Możliwe:");
            System.out.println(currency);
            chosenCurrency = input.nextLine();
            if (!isCorrectCurrencyInput(chosenCurrency)) {
                if (repeatAttempt()) {
                    continue;
                } else {
                    break;
                }
            }

            if(chosenAnalyze.equals("3")) {
                System.out.println("Podaj kod drugiej waluty z tabeli kursow walut dla ktorej przeprowadzic analize: ");
                System.out.println("Możliwe:");
                System.out.println(currency);
                secondChosenCurrency = input.nextLine();
                if (!isCorrectCurrencyInput(secondChosenCurrency) && !secondChosenCurrency.equals(chosenCurrency)) {
                    if (repeatAttempt()) {
                        continue;
                    } else {
                        break;
                    }
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
                if(chosenAnalyze.equals("1")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu A" +
                            " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                    listToSave = NbpSeriesA.analyzeSessions(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                } else if(chosenAnalyze.equals("2")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu A" +
                            " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                    listToSave = NbpSeriesA.analyzeStatisticalMeasures(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                } else if(chosenAnalyze.equals("3")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu A" +
                            " dla walut " + chosenCurrency + " i " + secondChosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);
                    NbpSeriesA.compareTwoCurrencies(getNbpSeriesAForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                            getNbpSeriesAForGivenCurrencyFromGivenPeriod(secondChosenCurrency, amountOfRecords), secondChosenCurrency);
                }
                System.out.println("\n");
            }

            if (chosenTable.equals("B")) {
                if(chosenAnalyze.equals("1")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu B" +
                            " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                    listToSave = NbpSeriesB.analyzeSessions(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                } else if(chosenAnalyze.equals("2")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu B" +
                            " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                    listToSave = NbpSeriesB.analyzeStatisticalMeasures(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                } else if(chosenAnalyze.equals("3")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu B" +
                            " dla walut " + chosenCurrency + " i " + secondChosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);
                    NbpSeriesB.compareTwoCurrencies(getNbpSeriesBForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                            getNbpSeriesBForGivenCurrencyFromGivenPeriod(secondChosenCurrency, amountOfRecords), secondChosenCurrency);
                }
                System.out.println("\n");
            }

            if (chosenTable.equals("C")) {
                if(chosenAnalyze.equals("1")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu C" +
                            " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                    listToSave = NbpSeriesC.analyzeSessions(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                } else if(chosenAnalyze.equals("2")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu C" +
                            " dla waluty " + chosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);

                    listToSave = NbpSeriesC.analyzeStatisticalMeasures(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords));
                } else if(chosenAnalyze.equals("3")) {
                    System.out.println("Analiza będzie przeprowadzona dla tabeli kursów walut typu C" +
                            " dla walut " + chosenCurrency + " i " + secondChosenCurrency + " w określonym okresie. Ilość rekordów: " + amountOfRecords);
                    NbpSeriesC.compareTwoCurrencies(getNbpSeriesCForGivenCurrencyFromGivenPeriod(chosenCurrency, amountOfRecords), chosenCurrency,
                            getNbpSeriesCForGivenCurrencyFromGivenPeriod(secondChosenCurrency, amountOfRecords), secondChosenCurrency);
                }
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

    public void saveResults() {
        try(FileWriter fw = new FileWriter("results.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
        {
            out.println("Analiza typ: " + chosenAnalyze + ", tabela: " + chosenTable + ", okres: "
                        + chosenPeriod + ", waluta: " + chosenCurrency);
            for(int i = 0; i < listToSave.size(); i++) {
                out.println(listToSave.get(i));
            }
            out.println("--------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Blad zapisu");
        }
    }

    private boolean exit() {

        if(!chosenAnalyze.equals("3")) {
            System.out.println("Czy chcesz zapisać wyniki ostatniej analizy? T/N");
            String answer = input.nextLine();

            if (answer.equals("T")) {
                saveResults();
            }
        }

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
        if(currency == null) {
            return false;
        }

        if (currency.contains(input)) {
            return true;
        }

        return false;
    }

    private void loadPossibleCurrency() {
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
        }
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

    private Boolean isCorrectAnalyzeInput(String input) {
        if(input.matches("[123]")) {
            return true;
        }
        return false;
    }

}
