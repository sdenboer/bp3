package com.example.bp3.views.fragments.Event;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bp3.R;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

/**
 * @author Koen Franken
 */

public class Event_Aanvragen extends ViewFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event__aanvragen, container, false);
    }

    @Override
    public int title() {
        return R.string.event_aanvragen;
    }
}