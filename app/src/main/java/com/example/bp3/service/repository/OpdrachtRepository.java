package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.Tag;

import java.util.Arrays;
import java.util.List;
/**
 * @author sven
 */
public class OpdrachtRepository extends AbstractRepository{

    private RestApiHelper restApiHelper;
    private static OpdrachtRepository opdrachtRepository;


    @Override
    protected String setUrlModel() {
        return "opdrachtvraag";
    }


    public void create(Opdracht opdracht, Toast onSuccess, Toast onError) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(opdracht, response -> onSuccess.show(), fail -> {
                    onError.show();
                    Log.d("Error", fail.toString());
                });
    }

    public LiveData<List<Opdracht>> getByTags(Tag tag) {
        final MutableLiveData<List<Opdracht>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(Opdracht[].class)
                .parameters(Arrays.asList("tags", tag.getTag()))
                .build();
        restApiHelper.getArray(ja -> {
            List<Opdracht> opdrachten = Arrays.asList((Opdracht[]) restApiHelper.toPOJO(ja));
            data.setValue(opdrachten);
        }, error -> Log.e("Kutzooi", error.toString()));
        return data;
    }

    public void delete(Opdracht opdracht) {
        RestApiHelper.prepareQuery(urlModel)
                .parameters(Arrays.asList(opdracht.getOpdrachtId()))
                .build()
                .delete(response -> Log.d("Good", "It's gone"), fail -> Log.d("Error", fail.toString()));
    }

    public LiveData<List<Opdracht>> getMyPostedOpdrachten() {
        Docent docent = (Docent) Account.currentUser;
        final MutableLiveData<List<Opdracht>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(Opdracht[].class)
                .parameters(Arrays.asList("my", docent.getEmail())).build();
        restApiHelper.getArray(ja -> {
            List<Opdracht> opdrachten = Arrays.asList((Opdracht[]) restApiHelper.toPOJO(ja));
            data.setValue(opdrachten);
        }, error -> Log.e("Kutzooi", error.toString()));
        return data;
    }

    public LiveData<Opdracht> getOpdrachtById(int id) {
        final MutableLiveData<Opdracht> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(Opdracht.class)
                .parameters(Arrays.asList(id)).build();
        restApiHelper.getObject(jo -> {
            Opdracht opdracht = (Opdracht) restApiHelper.toPOJO(jo);
            data.setValue(opdracht);
        }, error -> Log.e("Kutzooi", error.toString()));
        return data;
    }

    public void update(Opdracht opdracht) {
        RestApiHelper.prepareQuery(urlModel)
                .parameters(Arrays.asList(opdracht.getOpdrachtId()))
                .build()
                .update(opdracht, callback -> Log.d("UPDATE", "Het object is geupdate!"), error -> Log.e("Webservice Error", error.toString()));
    }

    public synchronized static OpdrachtRepository getInstance() {
        if (opdrachtRepository == null) {
            opdrachtRepository = new OpdrachtRepository();
        }
        return opdrachtRepository;
    }
}
