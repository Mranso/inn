package com.inn.inn.firstpage.model;

import java.io.Serializable;
import java.util.List;

public class TimeList implements Serializable{

    private boolean error;

    private List<String> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
