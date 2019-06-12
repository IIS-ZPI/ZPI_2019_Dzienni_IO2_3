package com.zpi.data.currencyData;

public class CurrencySesion {
    CurrencyTable currencyTable;

    public CurrencySesion(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }

    public int getCurrencyGrowthSessionAmount(String chosenSesion) {
        Rate lastRate = currencyTable.rates.get(0);
        int growthSessionAmount = 0;
        Boolean sesionStrted = false;
        for (int i = 1; i < currencyTable.rates.size(); i++) {
            if (lastRate.mid < currencyTable.rates.get(i).mid) {
                if (i == currencyTable.rates.size() - 1 && sesionStrted) {
                    growthSessionAmount++;
                }
                sesionStrted = true;
                lastRate = currencyTable.rates.get(i);
            } else {
                if (i > 1&&sesionStrted) {
                    lastRate = currencyTable.rates.get(i);
                    growthSessionAmount++;
                    sesionStrted = false;
                } else {
                    lastRate = currencyTable.rates.get(i);
                }
            }
        }
        return growthSessionAmount;
    }

}
