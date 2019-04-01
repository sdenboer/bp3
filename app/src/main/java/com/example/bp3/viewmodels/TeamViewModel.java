package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bp3.service.models.Team;
import com.example.bp3.service.repository.TeamRepository;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {

    public TeamViewModel(@NonNull Application application) {
        super(application);
    }

//    public List<Team> beschikbareTeams(int id) {
//        return TeamRepository.getInstance().beschikbareTeams(id);
//    }

    public LiveData<List<Team>> beschikbareTeams(int id) {
        return TeamRepository.getInstance().beschikbareTeams(id);
    }

    public void create(Team team) {
        TeamRepository.getInstance().create(team);
    }

    public void update(Team team) {
        TeamRepository.getInstance().update(team);
    }

    public void delete(Team team) {
        TeamRepository.getInstance().delete(team);
    }
}
