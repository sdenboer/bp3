package com.example.bp3.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.views.adapters.viewHolders.OpdrachtAanbodHolder;

import java.util.ArrayList;
import java.util.List;

public class OpdrachtAanbodAdapter extends RecyclerView.Adapter<OpdrachtAanbodHolder> {

    private List<OpdrachtAanbod> opdrachtAanbod = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public OpdrachtAanbodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_beschikbaar, viewGroup, false);
        return new OpdrachtAanbodHolder(itemView, listener, opdrachtAanbod);
    }

    @Override
    public void onBindViewHolder(@NonNull OpdrachtAanbodHolder opdrachtAanbodHolder, int i) {
        OpdrachtAanbod oa = this.opdrachtAanbod.get(i);
        opdrachtAanbodHolder.getLesvak().setText(oa.getOpdracht().getLesvak());
        opdrachtAanbodHolder.getBedrijf().setText(oa.getBedrijf().getNaam());
        opdrachtAanbodHolder.getOpdrachtnaam().setText(oa.getOpdracht().getOpdrachtNaam());
    }

    @Override
    public int getItemCount() {
        return opdrachtAanbod.size();
    }

    public void setOpdrachtAanbod(List<OpdrachtAanbod> oa) {
        this.opdrachtAanbod = oa;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(OpdrachtAanbod opdrachtAanbod);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
