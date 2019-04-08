package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.service.repository.TeamRepository;

import java.util.List;

/**
 * @author sven
 */
public class TeamViewModel extends AndroidViewModel {
    private Team team;

    public TeamViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Team>> beschikbareTeams(int id) {
        return TeamRepository.getInstance().beschikbareTeams(id);
    }

    public void create(String naam, OpdrachtAanbod opdrachtAanbod) {
        this.team = new Team(naam);
        team.addTeamMember((Student) Account.currentUser);
        TeamRepository.getInstance().create(team, opdrachtAanbod);
    }

    public void update(Team team) {
        TeamRepository.getInstance().update(team);
    }

    public void delete(Team team) {
        TeamRepository.getInstance().delete(team);
    }

    public Team getTeam() {
        return this.team;
    }

    public void addTeamMember(Team team) {
        team.addTeamMember((Student) Account.currentUser);
        TeamRepository.getInstance().update(team);
    }

}
