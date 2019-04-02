package com.example.bp3.service.repository;

public abstract class AbstractRepository {

    public String urlModel;

    public AbstractRepository() {
        this.urlModel = this.setUrlModel();
    }

    protected abstract String setUrlModel();

}
