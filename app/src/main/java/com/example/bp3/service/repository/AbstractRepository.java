package com.example.bp3.service.repository;

public abstract class AbstractRepository {

    public String urlModel;

    public AbstractRepository() {
        this.urlModel = this.getUrlModel();
    }

    protected abstract String getUrlModel();

}
