package com.example.bp3.views.adapters.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.models.OpdrachtAanbod;
import com.example.bp3.views.adapters.EventRecyclerViewAdapter;
import com.example.bp3.views.adapters.OpdrachtAanbodAdapter;

import java.util.List;

public class AanbodEventHolder extends RecyclerView.ViewHolder {
    private TextView EventSoort;
    private TextView EventNaam;
    private TextView EventDatum;



    public AanbodEventHolder(@NonNull View itemView, EventRecyclerViewAdapter.OnItemClickListener listener, List<AanbodEvent> aanbodEvents) {
        super(itemView);
        EventNaam = itemView.findViewById(R.id.textViewEventNaam);
        EventSoort = itemView.findViewById(R.id.textViewEventSoort);
        EventDatum = itemView.findViewById(R.id.textViewEventDatum);
        itemView.setOnClickListener(v -> {
            int i = getAdapterPosition();
            if (listener != null && i != RecyclerView.NO_POSITION) {
                listener.onItemClick(aanbodEvents.get(i));
            }
        });
    }

    public TextView getEventSoort() {
        return EventSoort;
    }

    public TextView getEventNaam() {
        return EventNaam;
    }

    public TextView getEventDatum() {
        return EventDatum;
    }
}
