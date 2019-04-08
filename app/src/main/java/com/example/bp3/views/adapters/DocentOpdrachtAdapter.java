package com.example.bp3.views.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.viewmodels.OpdrachtViewModel;
import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenDocent;
import com.example.bp3.views.fragments.Opdracht.Docent.OpdrachtVraagAdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sven
 */
public class DocentOpdrachtAdapter extends RecyclerView.Adapter<DocentOpdrachtAdapter.OpdrachtAdapterHolder> {

    private List<Opdracht> opdrachten = new ArrayList<>();
    private Fragment currentFragment;

    public DocentOpdrachtAdapter(Fragment fragment) {
        this.currentFragment = fragment;
    }

    @NonNull
    @Override
    public OpdrachtAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_mijn_activiteiten_docent, viewGroup, false);
        return new DocentOpdrachtAdapter.OpdrachtAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OpdrachtAdapterHolder opdrachtHolder, int i) {
        Opdracht opdracht = this.opdrachten.get(i);
        opdrachtHolder.getLesvak().setText(opdracht.getLesvak());
        opdrachtHolder.getOpdrachtnaam().setText(opdracht.getOpdrachtNaam());
        opdrachtHolder.getBtnEdit().setOnClickListener(e -> editOnClick(opdracht));
        opdrachtHolder.getBtnDelete().setOnClickListener(e -> deleteOnClick(opdracht));
    }

    private void deleteOnClick(Opdracht opdracht) {
        OpdrachtViewModel vm = ViewModelProviders.of(currentFragment).get(OpdrachtViewModel.class);
        vm.delete(opdracht);
        Fragment fragment = new MijnActiviteitenDocent();
        FragmentTransaction ft = currentFragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, "fragment");
        ft.commit();
    }

    private void editOnClick(Opdracht opdracht) {
        OpdrachtViewModel vm = ViewModelProviders.of(currentFragment).get(OpdrachtViewModel.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("opdracht", vm);
        bundle.putSerializable("opdrachtId", opdracht.getOpdrachtId());
        Fragment fragment = new OpdrachtVraagAdd();
        fragment.setArguments(bundle);
        FragmentTransaction ft = currentFragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment, "fragment");
        ft.commit();
    }

    @Override
    public int getItemCount() {
        return opdrachten.size();
    }

    public void setOpdrachten(List<Opdracht> opdrachten) {
        this.opdrachten = opdrachten;
        notifyDataSetChanged();
    }

    public class OpdrachtAdapterHolder extends RecyclerView.ViewHolder {
        private TextView lesvak;
        private TextView opdrachtnaam;
        private Button btnEdit;
        private Button btnDelete;


        public OpdrachtAdapterHolder(@NonNull View itemView) {
            super(itemView);
            lesvak = itemView.findViewById(R.id.mijn_activiteiten_docent_lesvak);
            opdrachtnaam = itemView.findViewById(R.id.mijn_activiteiten_docent_opdrachtnaam);
            btnEdit = itemView.findViewById(R.id.mijn_activiteiten_docent_edit);
            btnDelete = itemView.findViewById(R.id.mijn_activiteiten_docent_delete);
        }

        public Button getBtnEdit() {
            return btnEdit;
        }

        public Button getBtnDelete() {
            return btnDelete;
        }

        public TextView getLesvak() {
            return lesvak;
        }

        public TextView getOpdrachtnaam() {
            return opdrachtnaam;
        }
    }

}

