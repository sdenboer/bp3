package com.example.bp3.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Opdracht;

import java.util.ArrayList;
import java.util.List;

public class OpdrachtAdapter extends RecyclerView.Adapter<OpdrachtAdapter.OpdrachtAdapterHolder> {

    private List<Opdracht> opdrachten = new ArrayList<>();
    private OpdrachtAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public OpdrachtAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_mijn_activiteiten_docent, viewGroup, false);
        return new OpdrachtAdapter.OpdrachtAdapterHolder(itemView, opdrachten);
    }

    @Override
    public void onBindViewHolder(@NonNull OpdrachtAdapterHolder docentHolder, int i) {
        Opdracht opdracht = this.opdrachten.get(i);
        docentHolder.getLesvak().setText(opdracht.getLesvak());
        docentHolder.getOpdrachtnaam().setText(opdracht.getOpdrachtNaam());
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


        public OpdrachtAdapterHolder(@NonNull View itemView, List<Opdracht> opdrachten) {
            super(itemView);
            lesvak = itemView.findViewById(R.id.mijn_activiteiten_docent_lesvak);
            opdrachtnaam = itemView.findViewById(R.id.mijn_activiteiten_docent_opdrachtnaam);
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                if (listener != null && i != RecyclerView.NO_POSITION) {
                    listener.onItemClick(opdrachten.get(i));
                }
            });
        }

        public TextView getLesvak() {
            return lesvak;
        }

        public TextView getOpdrachtnaam() {
            return opdrachtnaam;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Opdracht opdracht);
    }

    public void setOnItemClickListener(OpdrachtAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}

