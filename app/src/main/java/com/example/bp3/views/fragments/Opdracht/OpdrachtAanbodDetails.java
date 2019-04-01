package com.example.bp3.views.fragments.Opdracht;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.databinding.FragmentOpdrachtaanbodDetailsBinding;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.viewmodels.TeamViewModel;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;
import com.example.bp3.views.adapters.TeamAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OpdrachtAanbodDetails extends ViewFragment {
    private TextView tv;
    FragmentOpdrachtaanbodDetailsBinding coadb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        OpdrachtAanbod oa = (OpdrachtAanbod) getArguments().getSerializable("opdracht");
        coadb = DataBindingUtil.inflate(inflater, R.layout.fragment_opdrachtaanbod_details, container, false);
        coadb.setOa(oa);
        View root = coadb.getRoot();
        coadb.setOa(oa);
        RecyclerView recyclerView = root.findViewById(R.id.opdrachtaanbod_teams_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        TeamViewModel vm = ViewModelProviders.of(this).get(TeamViewModel.class);
        LiveData<List<Team>> teams = vm.beschikbareTeams(oa.getId()); //is leeg
        Future<List<Team>> async = getTeams(teams);
        try {
            Log.d("HELLO", String.valueOf(async.get().size()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        final TeamAdapter adapter = new TeamAdapter(;
//        recyclerView.setAdapter(adapter);
        return root;
    }
    public Future<List<Team>> getTeams(LiveData<List<Team>> teams) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(1000);
            return teams.getValue();
        });
    }
    @Override
    public int title() {
        return R.string.projecten_opdrachten_details;
    }
}
