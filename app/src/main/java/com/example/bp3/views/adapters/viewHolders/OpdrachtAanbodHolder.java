package com.example.bp3.views.adapters.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;

import java.util.List;

public class OpdrachtAanbodHolder extends RecyclerView.ViewHolder {
    private TextView lesvak;
    private TextView bedrijf;
    private TextView opdrachtnaam;



    public OpdrachtAanbodHolder(@NonNull View itemView, OpdrachtAanbodAdapter.OnItemClickListener listener, List<OpdrachtAanbod> opdrachtAanbod) {
        super(itemView);
        lesvak = itemView.findViewById(R.id.opdracht_by_lesvak_lesvak);
        bedrijf = itemView.findViewById(R.id.opdracht_by_lesvak_bedrijf);
        opdrachtnaam = itemView.findViewById(R.id.opdracht_by_lesvak_opdrachtnaam);
        itemView.setOnClickListener(v -> {
            int i = getAdapterPosition();
            if (listener != null && i != RecyclerView.NO_POSITION) {
                listener.onItemClick(opdrachtAanbod.get(i));
            }
        });
    }

    public TextView getLesvak() {
        return lesvak;
    }

    public TextView getBedrijf() {
        return bedrijf;
    }

    public TextView getOpdrachtnaam() {
        return opdrachtnaam;
    }
}