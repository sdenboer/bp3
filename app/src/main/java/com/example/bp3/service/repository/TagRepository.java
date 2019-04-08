package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.bp3.service.models.Tag;

import java.util.Arrays;
import java.util.List;
/**
 * @author sven
 */
public class TagRepository extends AbstractRepository {
    private static TagRepository tagRepository;
    private RestApiHelper restApiHelper;

    @Override
    protected String setUrlModel() {
        return "tag";
    }

    public LiveData<List<Tag>> getTags() {
        final MutableLiveData<List<Tag>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel)
                .klasse(Tag[].class)
                .build();
        restApiHelper.getArray(jsonArray -> {
            data.setValue(Arrays.asList((Tag[]) restApiHelper.toPOJO(jsonArray)));
        }, error -> Log.e("Webservice Error", error.toString()));
        return data;
    }

    public void create(Tag tag) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(tag, response -> Log.d("Hoera", "De tag zit in de database"), error -> Log.e("Webservice Error", error.toString()));
    }

    public synchronized static TagRepository getInstance() {
        if (tagRepository == null) {
            tagRepository = new TagRepository();
        }
        return tagRepository;
    }
}
