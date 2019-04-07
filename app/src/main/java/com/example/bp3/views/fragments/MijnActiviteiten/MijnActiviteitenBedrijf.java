package com.example.bp3.views.fragments.MijnActiviteiten;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.views.adapters.BedrijfOpdrachtAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author sven
 */
public class MijnActiviteitenBedrijf extends ViewFragment {
    private OpdrachtAanbodViewModel vm;
    private BedrijfOpdrachtAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_mijn_activiteiten, container, false);
        setRecyclerView(root);
        return root;
    }

    private void setRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.opdracht_docent_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        BedrijfOpdrachtAdapter adapter = setAdapter();
        recyclerView.setAdapter(adapter);
    }

    private BedrijfOpdrachtAdapter setAdapter() {
        adapter = new BedrijfOpdrachtAdapter(this);
        vm = ViewModelProviders.of(this).get(OpdrachtAanbodViewModel.class);
        vm.getBedrijfZietOpdrachten().observe(this, adapter::setOpdrachten);
        return adapter;
    }

    @Override
    public int title() {
        return R.string.projecten_mystuff;
    }
}

