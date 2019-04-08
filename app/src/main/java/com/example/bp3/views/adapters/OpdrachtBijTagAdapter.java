package com.example.bp3.views.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.views.fragments.Opdracht.Bedrijf.OpdrachtAanbodAdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sven
 */
public class OpdrachtBijTagAdapter extends RecyclerView.Adapter<OpdrachtBijTagAdapter.OpdrachBijTagHolder> {

    private List<Opdracht> opdrachten = new ArrayList<>();
    private OpdrachtBijTagAdapter.OnItemClickListener listener;
    private FragmentActivity currentFragment;


    public OpdrachtBijTagAdapter(FragmentActivity currentFragment) {
        this.currentFragment = currentFragment;
    }

    @NonNull
    @Override
    public OpdrachtBijTagAdapter.OpdrachBijTagHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtvraag_available, viewGroup, false);
        return new OpdrachBijTagHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OpdrachtBijTagAdapter.OpdrachBijTagHolder teamHolder, int i) {
        Opdracht o = this.opdrachten.get(i);
        teamHolder.getTagTitle().setText(o.getOpdrachtNaam());
        teamHolder.getTagNiveau().setText(o.getDocent().getOpleiding().getNiveau());
        teamHolder.getTagInstelling().setText(o.getDocent().getOpleiding().getOpleidingPK().getOnderwijsinstelling());

        teamHolder.getTagEisen().setText(o.getEisen());
        teamHolder.getTagMin().setText("Aantal studenten minimaal: " + String.valueOf(o.getAantStudMin()));
        teamHolder.getTagMax().setText("Aantal studenten maximaal: " + String.valueOf(o.getAantStudMax()));
        teamHolder.getTagDeadline().setText("Deadline: " + o.getPrettyDeadline());
        teamHolder.getTagButton().setOnClickListener(e -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("opdracht", o);
            Fragment fragment = new OpdrachtAanbodAdd();
            fragment.setArguments(bundle);
            FragmentTransaction ft = currentFragment.getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        });
    }

    @Override
    public int getItemCount() {
        return opdrachten.size();
    }

    public void setOpdrachten(List<Opdracht> t) {
        this.opdrachten = t;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Opdracht t);
    }

    public void setOnItemClickListener(OpdrachtBijTagAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public class OpdrachBijTagHolder extends RecyclerView.ViewHolder {
        private TextView tagTitle;
        private TextView tagNiveau;
        private TextView tagInstelling;
        private Button tagButton;
        private TextView tagEisen;
        private TextView tagMin;
        private TextView tagMax;
        private TextView tagDeadline;


        public OpdrachBijTagHolder(@NonNull View itemView) {
            super(itemView);
            tagTitle = itemView.findViewById(R.id.tag_title);
            tagNiveau = itemView.findViewById(R.id.tag_niveau);
            tagInstelling = itemView.findViewById(R.id.tag_instelling);
            tagButton = itemView.findViewById(R.id.tag_opdracht_add_btn);
            tagEisen = itemView.findViewById(R.id.tag_eisen);
            tagMin = itemView.findViewById(R.id.tag_min);
            tagMax = itemView.findViewById(R.id.tag_max);
            tagDeadline = itemView.findViewById(R.id.tag_deadline);
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                if (listener != null && i != RecyclerView.NO_POSITION) {
                    listener.onItemClick(opdrachten.get(i));
                }
            });
        }

        public TextView getTagTitle() {
            return tagTitle;
        }

        public TextView getTagNiveau() {
            return tagNiveau;
        }

        public TextView getTagInstelling() {
            return tagInstelling;
        }

        public Button getTagButton() {
            return tagButton;
        }

        public TextView getTagEisen() {
            return tagEisen;
        }

        public TextView getTagMin() {
            return tagMin;
        }

        public TextView getTagMax() {
            return tagMax;
        }

        public TextView getTagDeadline() {
            return tagDeadline;
        }
    }
}
