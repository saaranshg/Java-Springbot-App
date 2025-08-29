package com.webhook.sqlsolver.model;

public class SqlSolutionRequest {
    private String finalQuery;

    public SqlSolutionRequest() {}

    public SqlSolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
}
