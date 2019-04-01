package com.example.bp3.views.fragments.Opdracht;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.util.ArrayList;

public class OpdrachtLesvak extends ViewFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_opdrachtaanbod_lesvak, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.opdracht_lesvak_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final OpdrachtAanbodAdapter adapter = new OpdrachtAanbodAdapter();
        OpdrachtAanbodViewModel vm = ViewModelProviders.of(this).get(OpdrachtAanbodViewModel.class);
        vm.getStudentZietOpdrachten("Avans", "AD Informatica", 1).observe(this, adapter::setOpdrachtAanbod);
        adapter.setOnItemClickListener(opdrachtAanbod -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("opdracht", opdrachtAanbod);
            Fragment fragment = new OpdrachtAanbodDetails();
            fragment.setArguments(bundle);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public int title() {
        return R.string.projecten_opdrachten;
    }
}
