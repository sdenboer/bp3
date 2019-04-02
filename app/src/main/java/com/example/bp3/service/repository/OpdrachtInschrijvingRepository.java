package com.example.bp3.service.repository;

import android.util.Log;

import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.OpdrachtInschrijving;

public class OpdrachtInschrijvingRepository extends AbstractRepository{

    private RestApiHelper restApiHelper;
    private static OpdrachtInschrijvingRepository opdrachtAanbodRepository;

    public void create(OpdrachtInschrijving opdrachtInschrijving) {
        RestApiHelper.prepareQuery("team")
                .build()
                .post(opdrachtInschrijving, response -> Log.d("POST", "Het object zit in de database!"));
    }

    @Override
    protected String setUrlModel() {
        return "team";
    }
}
