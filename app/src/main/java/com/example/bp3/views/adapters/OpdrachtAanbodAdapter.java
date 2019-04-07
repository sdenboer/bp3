package com.example.bp3.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.OpdrachtAanbod;
import java.util.ArrayList;
import java.util.List;
/**
 * @author sven
 */
public class OpdrachtAanbodAdapter extends RecyclerView.Adapter<OpdrachtAanbodAdapter.OpdrachtAanbodHolder> {

    private List<OpdrachtAanbod> opdrachtAanbod = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public OpdrachtAanbodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_opdrachtaanbod_beschikbaar, viewGroup, false);
        return new OpdrachtAanbodHolder(itemView, opdrachtAanbod);
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

    public class OpdrachtAanbodHolder extends RecyclerView.ViewHolder {
        private TextView lesvak;
        private TextView bedrijf;
        private TextView opdrachtnaam;


        public OpdrachtAanbodHolder(@NonNull View itemView, List<OpdrachtAanbod> opdrachtAanbod) {
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

    public interface OnItemClickListener {
        void onItemClick(OpdrachtAanbod opdrachtAanbod);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
