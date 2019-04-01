package com.example.bp3.views.fragments.Opdracht;

import android.app.Dialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.databinding.CardOpdrachtaanbodTeamnaamBinding;
import com.example.bp3.databinding.FragmentOpdrachtaanbodDetailsBinding;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.models.Team;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.viewmodels.TeamViewModel;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;
//import com.example.bp3.views.adapters.TeamAdapter;
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
        final TeamAdapter adapter = new TeamAdapter();
        vm.beschikbareTeams(oa.getId()).observe(this, adapter::setTeams);
        adapter.setOnItemClickListener(t -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("opdracht", oa);
            bundle.putSerializable("team", t);
            DialogFragment fragment = new TeamMembersDialog();
            fragment.setArguments(bundle);
            fragment.show(getFragmentManager(), "example");
//            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.fragment_container, fragment);
//            ft.commit();
        });
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public int title() {
        return R.string.projecten_opdrachten_details;
    }
}
