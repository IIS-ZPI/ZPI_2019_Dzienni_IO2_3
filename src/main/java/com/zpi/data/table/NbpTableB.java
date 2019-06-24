package com.zpi.data.table;

import com.zpi.data.rates.Rates;

import java.util.List;

public class NbpTableB {
    String table;
    String no;
    String effectiveDate;
    List<Rates> rates;

    public String getTable() {
        return table;
    }

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public List<Rates> getRates() {
        return rates;
    }
}
