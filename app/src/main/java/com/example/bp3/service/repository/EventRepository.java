package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.Inschrijvingsnummer;
import java.util.Arrays;
import java.util.List;

/**
 * @author Koen Franken
 */

public class EventRepository {
    private RestApiHelper restApiHelper;
    private static EventRepository eventRepository;

    public LiveData<List<AanbodEvent>> getAllAanbodevent() {
        final MutableLiveData<List<AanbodEvent>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery("aanbodevent")
                .klasse(AanbodEvent[].class)
                .build();
        restApiHelper.getArray(jsonArray -> {
            data.setValue(Arrays.asList((AanbodEvent[]) restApiHelper.toPOJO(jsonArray)));

        });
        return data;
    }

    public void create(AanbodEvent aanbodEvent) {
        RestApiHelper.prepareQuery("aanbodevent")
                .build()
                .post(aanbodEvent, response -> Log.d("POST", "Het object zit in de database!"));
                        
    }
    public void createInschrijving(Inschrijvingsnummer inschrijvingsnummer) {
        RestApiHelper.prepareQuery("inschrijvingsnummer")
                .build()
                .post(inschrijvingsnummer, response -> Log.d("POST", "Het object zit in de database!"));
    }

    public void update(AanbodEvent aanbodEvent) {
        RestApiHelper.prepareQuery("aanbodevent")
                //.parameters(Arrays.asList(aanbodEvent.geteventnummer()))
                .build()
                .update(aanbodEvent, callback -> Log.d("UPDATE", "Het object is geupdate!"));
    }

    public void delete(AanbodEvent aanbodEvent) {
        RestApiHelper.prepareQuery("aanbodevent")
                //.parameters(Arrays.asList(aanbodEvent.geteventnummer()))
                .build()
                .delete(callback -> Log.d("DELETE", "Het object is verwijderd!"));
    }

    public synchronized static EventRepository getInstance() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        return eventRepository;
    }
}
