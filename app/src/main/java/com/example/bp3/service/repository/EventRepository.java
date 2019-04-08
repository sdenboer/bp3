package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.bp3.service.models.AanbodEvent;
import java.util.Arrays;
import java.util.List;

/**
 * @author Koen Franken
 */

public class EventRepository extends AbstractRepository {
    private RestApiHelper restApiHelper;
    private static EventRepository eventRepository;

    @Override
    protected String setUrlModel() {
        return "aanbodevent";
    }

    public LiveData<List<AanbodEvent>> getAllAanbodevent() {
        final MutableLiveData<List<AanbodEvent>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(AanbodEvent[].class)
                .build();
        restApiHelper.getArray(jsonArray -> {
            data.setValue(Arrays.asList((AanbodEvent[]) restApiHelper.toPOJO(jsonArray)));

        },
                error -> Log.d("Error", error.toString()));
        return data;
    }

    public void create(AanbodEvent aanbodEvent) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(aanbodEvent, response -> Log.d("POST", "Het object zit in de database!"),
                        error -> Log.d("Error", error.toString()));
                        
    }
    public void update(AanbodEvent aanbodEvent) {
        RestApiHelper.prepareQuery(urlModel)
                //.parameters(Arrays.asList(aanbodEvent.geteventnummer()))
                .build()
                .update(aanbodEvent, callback -> Log.d("UPDATE", "Het object is geupdate!"),
                        error -> Log.d("Error", error.toString()));
    }

    public void delete(AanbodEvent aanbodEvent) {
        RestApiHelper.prepareQuery(urlModel)
                //.parameters(Arrays.asList(aanbodEvent.geteventnummer()))
                .build()
                .delete(callback -> Log.d("DELETE", "Het object is verwijderd!"),
                        error -> Log.d("Error", error.toString()));
    }

    public synchronized static EventRepository getInstance() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        return eventRepository;
    }
}
