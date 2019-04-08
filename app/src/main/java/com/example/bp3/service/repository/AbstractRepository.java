package com.example.bp3.service.repository;

/**
 * @author sven
 * Deze methode maakt het makkelijker om een URL model aan te geven in de RESTAPIHELPER.
 */
public abstract class AbstractRepository {

    public String urlModel;

    public AbstractRepository() {
        this.urlModel = this.setUrlModel();
    }

    protected abstract String setUrlModel();

}
