package com.example.bp3.service.repository;

import android.util.Log;

import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;

import java.util.HashMap;

public class OpdrachtRepository extends AbstractRepository{

    private RestApiHelper restApiHelper;
    private static OpdrachtRepository opdrachtRepository;


    @Override
    protected String setUrlModel() {
        return "opdrachtvraag";
    }


    public void create(Opdracht opdracht) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(opdracht, response -> Log.d("POST", "Het object zit in de database!"));
    }

    public synchronized static OpdrachtRepository getInstance() {
        if (opdrachtRepository == null) {
            opdrachtRepository = new OpdrachtRepository();
        }
        return opdrachtRepository;
    }
}
