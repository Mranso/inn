package com.inn.inn.secondpage.model;


import com.inn.inn.firstpage.model.DayBaseData;

import java.util.List;

public class BeautyListResult {

    private boolean error;
    private List<DayBaseData> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DayBaseData> getResults() {
        return results;
    }

    public void setResults(List<DayBaseData> results) {
        this.results = results;
    }
}
