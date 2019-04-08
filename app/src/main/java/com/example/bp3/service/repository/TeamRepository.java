package com.example.bp3.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Team;

import java.util.Arrays;
import java.util.List;
/**
 * @author sven
 */
public class TeamRepository extends AbstractRepository {

    private RestApiHelper restApiHelper;
    private static TeamRepository teamRepository;


    @Override
    protected String setUrlModel() {
        return "team";
    }

    public void create(Team team, OpdrachtAanbod opdrachtAanbod) {
        RestApiHelper.prepareQuery(urlModel)
                .build()
                .post(team, response -> {
                    opdrachtAanbod.opdrachtInschrijving(team);
                    OpdrachtAanbodRepository.getInstance().update(opdrachtAanbod);
                }, error -> Log.e("Webservice Error", error.toString()));
    }

    public LiveData<List<Team>> beschikbareTeams(int opdrachtId) {
        final MutableLiveData<List<Team>> data = new MutableLiveData<>();
        restApiHelper = RestApiHelper.prepareQuery(urlModel).klasse(Team[].class).parameters(Arrays.asList("find", opdrachtId)).build();
        restApiHelper.getArray(jsonArray -> data.setValue(Arrays.asList((Team[]) restApiHelper.toPOJO(jsonArray))), error -> Log.e("Webservice Error", error.toString()));
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
                .update(team, callback -> Log.d("UPDATE", "Het object is geupdate!"), error -> Log.e("Webservice Error", error.toString()));
    }

    public void delete(Team team) {
        RestApiHelper.prepareQuery(urlModel)
                .parameters(Arrays.asList(team.getTeamNaam()))
                .build()
                .delete(callback -> Log.d("DELETE", "Het object is verwijderd!"), error -> Log.e("Webservice Error", error.toString()));
    }

    public synchronized static TeamRepository getInstance() {
        if (teamRepository == null) {
            teamRepository = new TeamRepository();
        }
        return teamRepository;
    }


}
