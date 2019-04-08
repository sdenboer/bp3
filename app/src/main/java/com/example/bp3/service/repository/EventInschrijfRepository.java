package com.example.bp3.service.repository;

import android.util.Log;

import com.android.volley.VolleyError;
import com.example.bp3.service.models.Inschrijvingsnummer;

/**
 * @author Koen Franken
 */

public class EventInschrijfRepository extends AbstractRepository {
    private RestApiHelper restApiHelper;
    private static EventInschrijfRepository eventInschrijfRepository;

    @Override
    protected String setUrlModel() {
        return "inschrijvingsnummer";
    }

    public void create(Inschrijvingsnummer inschrijvingsnummer) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(inschrijvingsnummer, response -> Log.d("POST", "Het object zit in de database!"),
                        error -> Log.d("Error", error.toString()));
    }

    public void update(Inschrijvingsnummer inschrijvingsnummer) {
        RestApiHelper.prepareQuery(urlModel)
                //.parameters(Arrays.asList(inschrijvingsnummer.getinschrijfnummer()))
                .build()
                .update(inschrijvingsnummer, callback -> Log.d("UPDATE", "Het object is geupdate!"),
                        error -> Log.d("Error", error.toString()));
    }

    public void delete(Inschrijvingsnummer inschrijvingsnummer) {
        RestApiHelper.prepareQuery(urlModel)
                //.parameters(Arrays.asList(inschrijvingsnummer.getinschrijfnummer()))
                .build()
                .delete(callback -> Log.d("DELETE", "Het object is verwijderd!"),
                        error -> Log.d("Error", error.toString()));
    }

    public synchronized static EventInschrijfRepository getInstance() {
        if (eventInschrijfRepository == null) {
            eventInschrijfRepository = new EventInschrijfRepository();
        }
        return eventInschrijfRepository;
    }
}
