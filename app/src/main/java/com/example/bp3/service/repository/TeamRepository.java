package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Team;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamRepository extends AbstractRepository{

    private RestApiHelper restApiHelper;
    private static TeamRepository teamRepository;


    @Override
    protected String getUrlModel() {
        return "team";
    }

    public void create(Team team) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(team, response -> Log.d("POST", "Het object zit in de database!"));
    }

    public LiveData<List<Team>> beschikbareTeams(int opdrachtId) {
        final MutableLiveData<List<Team>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel).klasse(Team[].class).parameters(Arrays.asList("find", opdrachtId)).build();
        restApiHelper.getArray(jsonArray -> data.setValue(Arrays.asList((Team[]) restApiHelper.toPOJO(jsonArray))));
        return data;
    }

//    public List<Team> beschikbareTeams(int opdrachtId) {
//        final List<Team> data = new ArrayList<>();
//        restApiHelper = RestApiHelper.prepareQuery(urlModel).klasse(Team[].class).parameters(Arrays.asList("find", opdrachtId)).build();
//        restApiHelper.getArray(jsonArray -> {
//            JSONArray t = jsonArray;
//            Log.d("Async", String.valueOf(t.length()));
//            data.addAll(Arrays.asList((Team[]) restApiHelper.toPOJO(jsonArray)));
//        });
//        Log.d("Main thread", String.valueOf(data.size()));
//        return data;
//    }

    public void update(Team team) {
        RestApiHelper.prepareQuery(urlModel)
                .parameters(Arrays.asList(team.getTeamNaam()))
                .build()
                .update(team, callback -> Log.d("UPDATE", "Het object is geupdate!"));
    }

    public void delete(Team team) {
        RestApiHelper.prepareQuery("opdrachtaanbod")
                .parameters(Arrays.asList(team.getTeamNaam()))
                .build()
                .delete(callback -> Log.d("DELETE", "Het object is verwijderd!"));
    }

    public synchronized static TeamRepository getInstance() {
        if (teamRepository == null) {
            teamRepository = new TeamRepository();
        }
        return teamRepository;
    }



}
