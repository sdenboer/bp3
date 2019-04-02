package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Tag;

import java.util.Arrays;
import java.util.List;

public class EventRepository {
    private RestApiHelper restApiHelper;
    private static EventRepository eventRepository;

    public void getAllAanbodevents() {
        RestApiHelper eventJSON = RestApiHelper
                .prepareQuery("aanbodevent")
                .klasse(AanbodEvent[].class)
                .build();
        eventJSON.getArray(ja -> {
            List<AanbodEvent> events = Arrays.asList((AanbodEvent[]) eventJSON.toPOJO(ja));
            //      In deze array zitten alle tag objecten.
            //      voeg hieronder code toe om iets met de objecten te doen. Bijvoorbeeld:
            //
            events.forEach(event -> Log.d("aanbodevent", event.getNaam()));
        });
    }

    public synchronized static EventRepository getInstance() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        return eventRepository;
    }
}
