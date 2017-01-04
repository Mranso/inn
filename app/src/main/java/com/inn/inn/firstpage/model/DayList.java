package com.inn.inn.firstpage.model;

import java.util.List;

public class DayList {

    private List<String> category ;

    private boolean error;

    private DataTypeResult results;

    public void setString(List<String> category){
        this.category = category;
    }
    public List<String> getString(){
        return this.category;
    }
    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }
    public void setResults(DataTypeResult results){
        this.results = results;
    }
    public DataTypeResult getResults(){
        return this.results;
    }
}
