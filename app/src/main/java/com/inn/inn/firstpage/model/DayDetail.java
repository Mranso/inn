package com.inn.inn.firstpage.model;

import java.io.Serializable;
import java.util.List;

public class DayDetail implements Serializable{

    private List<String> category ;

    private boolean error;

    private DataTypeResult results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DataTypeResult getResults() {
        return results;
    }

    public void setResults(DataTypeResult results) {
        this.results = results;
    }
}
