package com.zpi.data.currencyData;

import java.util.List;

public class CurrencyTable {

    public String table;
    public String currency;
    public String code;
    public List<Rate> rates = null;

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public static void analyze(CurrencyTable currencyTable){
        double[] valuesCurrency = new double[currencyTable.getRates().size()];

        for (int i = 0; i < currencyTable.getRates().size(); i++) {
            valuesCurrency[i] = currencyTable.getRates().get(i).getMid();
        }
    }



}
