package com.inn.inn.thirdpage.model;

import java.util.List;

public class ResourceDataResult {

    private boolean error;
    private List<ResourceItemData> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResourceItemData> getResults() {
        return results;
    }

    public void setResults(List<ResourceItemData> results) {
        this.results = results;
    }
}
