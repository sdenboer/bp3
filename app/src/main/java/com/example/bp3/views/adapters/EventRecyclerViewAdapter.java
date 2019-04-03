package com.example.bp3.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import com.example.bp3.R;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.views.adapters.viewHolders.AanbodEventHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Koen Franken
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<AanbodEventHolder> {

    private List<AanbodEvent> aanbodEvents = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AanbodEventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_cardview, viewGroup, false);
        return new AanbodEventHolder(itemView, listener, aanbodEvents);
    }

    @Override
    public void onBindViewHolder(@NonNull AanbodEventHolder aanbodEventHolder, int i) {
        AanbodEvent event = this.aanbodEvents.get(i);
        aanbodEventHolder.getEventNummer().setText(Integer.toString(event.getEventnummer()));
        aanbodEventHolder.getEventNaam().setText(event.getNaam());
        aanbodEventHolder.getEventDatum().setText(event.getDatumentijd());
    }

    @Override
    public int getItemCount() {
        return aanbodEvents.size();
    }

    public void setAanbodEvent(List<AanbodEvent> event) {
        this.aanbodEvents = event;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(AanbodEvent aanbodEvent);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
