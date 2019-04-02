package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.OpdrachtInschrijving;

import java.util.Arrays;
import java.util.List;


public class OpdrachtAanbodRepository extends AbstractRepository{

    private RestApiHelper restApiHelper;
    private static OpdrachtAanbodRepository opdrachtAanbodRepository;

    public void create(OpdrachtAanbod opdrachtAanbod) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(opdrachtAanbod, response -> Log.d("POST", "Het object zit in de database!"));
    }

    public LiveData<List<OpdrachtAanbod>> studentZietOpdrachtenLeskvak(String instelling, String opleiding, Integer leerjaar) {
        final MutableLiveData<List<OpdrachtAanbod>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(OpdrachtAanbod[].class)
                .parameters(Arrays.asList(instelling, opleiding, leerjaar))
                .build();
        restApiHelper.getArray(jsonArray -> {
            data.setValue(Arrays.asList((OpdrachtAanbod[]) restApiHelper.toPOJO(jsonArray)));
        });
        return data;
    }

    public LiveData<List<OpdrachtAanbod>> studentZietEigenOpdrachten(String email) {
        final MutableLiveData<List<OpdrachtAanbod>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(OpdrachtAanbod[].class)
                .parameters(Arrays.asList("my", email))
                .build();
        restApiHelper.getArray(jsonArray -> {
            data.setValue(Arrays.asList((OpdrachtAanbod[]) restApiHelper.toPOJO(jsonArray)));
        });
        return data;
    }


//    public List<OpdrachtInschrijving> opdrachtInschrijving(int id) {
//        final MutableLiveData<List<OpdrachtInschrijving>> data = new MutableLiveData<>();
//        restApiHelper = RestApiHelper.prepareQuery("opdrachtaanbod")
//                .klasse(OpdrachtInschrijving[].class)
//                .parameters(Arrays.asList(id))
//                .build();
//        restApiHelper.getArray(jsonArray -> data.setValue(Arrays.asList((OpdrachtInschrijving[]) restApiHelper.toPOJO(jsonArray))));
//        return data;
//    }

    public LiveData<List<OpdrachtAanbod>> opdrachtAanbodByVraagId(int id) {
        final MutableLiveData<List<OpdrachtAanbod>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(OpdrachtAanbod[].class)
                .parameters(Arrays.asList("vraag", id))
                .build();
        restApiHelper.getArray(jsonArray -> data.setValue(Arrays.asList((OpdrachtAanbod[]) restApiHelper.toPOJO(jsonArray))));
        return data;
    }



    public void update(OpdrachtAanbod opdrachtAanbod) {
        RestApiHelper.prepareQuery(urlModel)
                .parameters(Arrays.asList(opdrachtAanbod.getId())) //<- In dit geval is "mbo" de primary key van het object dat gewijzigd moet worden
                .build()
                .update(opdrachtAanbod, callback -> Log.d("UPDATE", "Het object is geupdate!"));
    }

    public void delete(OpdrachtAanbod opdrachtAanbod) {
        RestApiHelper.prepareQuery(urlModel)
                .parameters(Arrays.asList(opdrachtAanbod.getId()))
                .build()
                .delete(callback -> Log.d("DELETE", "Het object is verwijderd!"));
    }


    public synchronized static OpdrachtAanbodRepository getInstance() {
        if (opdrachtAanbodRepository == null) {
            opdrachtAanbodRepository = new OpdrachtAanbodRepository();
        }
        return opdrachtAanbodRepository;
    }

    @Override
    protected String setUrlModel() {
        return "opdrachtaanbod";
    }
}
