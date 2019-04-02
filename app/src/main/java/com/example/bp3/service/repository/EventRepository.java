package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.OpdrachtAanbod;

import java.util.Arrays;
import java.util.List;

public class EventRepository {
    private RestApiHelper restApiHelper;
    private static EventRepository eventRepository;

    public LiveData<List<AanbodEvent>> getAanbobeventById(int id) {
        final MutableLiveData<List<AanbodEvent>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery("aanbodevent")
                .klasse(AanbodEvent[].class)
                .parameters(Arrays.asList(id))
                .build();
        restApiHelper.getArray(jsonArray -> data.setValue(Arrays.asList((AanbodEvent[]) restApiHelper.toPOJO(jsonArray))));
        return data;
    }

    public synchronized static EventRepository getInstance() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        return eventRepository;
    }
}
