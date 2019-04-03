package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.bp3.service.models.AanbodEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventRepository {
    private RestApiHelper restApiHelper;
    private static EventRepository eventRepository;
    private ArrayList<AanbodEvent> aanbodevents;

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

    public ArrayList<AanbodEvent> getAllAanbodevents() {
        aanbodevents = new ArrayList<>();
        RestApiHelper eventJSON = RestApiHelper
                .prepareQuery("aanbodevent")
                .klasse(AanbodEvent[].class)
                .build();
        eventJSON.getArray(ja -> {
            List<AanbodEvent> events = Arrays.asList((AanbodEvent[]) eventJSON.toPOJO(ja));
            //      In deze array zitten alle tag objecten.
            //      voeg hieronder code toe om iets met de objecten te doen. Bijvoorbeeld:
            //

            events.forEach(event -> aanbodevents.add(event));

        });

        return aanbodevents;
    }

    public List<AanbodEvent> EventByVraagId(int id) {
        final List<AanbodEvent> data = new ArrayList<>();

        restApiHelper = RestApiHelper.prepareQuery("aanbodevent")
                .klasse(AanbodEvent[].class)
                .build();
        //restApiHelper.getArray(jsonArray -> data.setValue(Arrays.asList((AanbodEvent[]) restApiHelper.toPOJO(jsonArray))));
        restApiHelper.getArray(ja -> {
            List<AanbodEvent> events = Arrays.asList((AanbodEvent[]) restApiHelper.toPOJO(ja));



            });
    return null;
    }
        public void aanbodevent() {
            final ArrayList<AanbodEvent> data = new ArrayList<>();

                try {
                    JSONObject obj = new JSONObject("192.168.178.13:8080/bp3webservice/webresources/models.");

                    // fetch JSONObject named employee
                    JSONObject employee = obj.getJSONObject("aanbodevent");
                    // get employee name
                    String name = employee.getString("naam");
                    System.out.println(name);

                }catch (JSONException e) {
                    e.printStackTrace();
                }
/*
                JSONObject reader = new JSONObject(jsonArray);
                JSONObject m = reader.getJSONObject("ff");// new JSONObject(jsonArray);
                String naam = m.getString("naam");

            System.out.println(data.getValue());
            */
        }

    public synchronized static EventRepository getInstance() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        return eventRepository;
    }
}
