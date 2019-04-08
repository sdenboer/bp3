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
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.viewmodels.OpdrachtAanbodViewModel;
import com.example.bp3.views.fragments.MijnActiviteiten.MijnActiviteitenBedrijf;
import com.example.bp3.views.fragments.Opdracht.Bedrijf.OpdrachtAanbodAdd;
import com.example.bp3.views.fragments.Opdracht.Student.OpdrachtAanbodDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sven
 */
public class BedrijfOpdrachtAdapter extends RecyclerView.Adapter<BedrijfOpdrachtAdapter.BedrijfOpdrachtAdapterHolder> {

    private List<OpdrachtAanbod> opdrachten = new ArrayList<>();
    private Fragment currentFragment;

    public BedrijfOpdrachtAdapter(Fragment fragment) {
        this.currentFragment = fragment;
    }

    @NonNull
    @Override
    public BedrijfOpdrachtAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_mijn_activiteiten_bedrijf, viewGroup, false);
        return new BedrijfOpdrachtAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BedrijfOpdrachtAdapterHolder opdrachtHolder, int i) {
        OpdrachtAanbod opdracht = this.opdrachten.get(i);
        opdrachtHolder.getOpdrachtnaam().setText(opdracht.getOpdracht().getOpdrachtNaam());
        opdrachtHolder.getNiveau().setText(opdracht.getOpdracht().getDocent().getOpleiding().getNiveau());
        opdrachtHolder.getInstelling().setText(opdracht.getOpdracht().getDocent().getOpleiding().getOpleidingPK().getOnderwijsinstelling());
        opdrachtHolder.getTeams().setText(String.valueOf(opdracht.getTeams().size()));
        opdrachtHolder.getLesvak().setText(opdracht.getOpdracht().getLesvak());
        opdrachtHolder.getEdit().setOnClickListener(e -> editOnClick(opdracht));
        opdrachtHolder.getDelete().setOnClickListener(e -> deleteOnClick(opdracht));
        opdrachtHolder.getView().setOnClickListener(e -> viewOnClick(opdracht));
    }

    private void deleteOnClick(OpdrachtAanbod opdracht) {
        OpdrachtAanbodViewModel vm = ViewModelProviders.of(currentFragment).get(OpdrachtAanbodViewModel.class);
        vm.delete(opdracht);
        Fragment fragment = new MijnActiviteitenBedrijf(); //SHOULD RELOAD
        FragmentTransaction ft = currentFragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, "fragment");
        ft.commit();
    }

    private void editOnClick(OpdrachtAanbod opdracht) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("opdrachtAanbod", opdracht);
        Fragment fragment = new OpdrachtAanbodAdd();
        fragment.setArguments(bundle);
        FragmentTransaction ft = currentFragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment, "fragment");
        ft.commit();
    }

    private void viewOnClick(OpdrachtAanbod opdracht) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("opdracht", opdracht);
        Fragment fragment = new OpdrachtAanbodDetails();
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

    public void setOpdrachten(List<OpdrachtAanbod> opdrachten) {
        this.opdrachten = opdrachten;
        notifyDataSetChanged();
    }

    public class BedrijfOpdrachtAdapterHolder extends RecyclerView.ViewHolder {
        private TextView opdrachtnaam;
        private TextView niveau;
        private TextView lesvak;
        private TextView teams;
        private TextView instelling;
        private Button edit;
        private Button delete;
        private Button view;


        public BedrijfOpdrachtAdapterHolder(@NonNull View itemView) {
            super(itemView);
            lesvak = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_lesvak);
            opdrachtnaam = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_opdrachtnaam);
            niveau = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_niveau);
            teams = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_teams);
            view = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_view);
            edit = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_edit);
            instelling = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_instelling);
            delete = itemView.findViewById(R.id.mijn_activiteiten_bedrijf_delete);
        }

        private TextView getOpdrachtnaam() {
            return opdrachtnaam;
        }

        private TextView getNiveau() {
            return niveau;
        }

        private TextView getLesvak() {
            return lesvak;
        }

        private TextView getTeams() {
            return teams;
        }

        private Button getEdit() {
            return edit;
        }

        private Button getDelete() {
            return delete;
        }

        private Button getView() {
            return view;
        }

        public TextView getInstelling() {
            return instelling;
        }
    }

}

