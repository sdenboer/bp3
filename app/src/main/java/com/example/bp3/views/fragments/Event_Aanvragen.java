package com.example.bp3.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bp3.R;
import com.example.bp3.views.fragments.ViewFragment;

public class Event_Aanvragen extends ViewFragment {

    @Override
    protected int layoutFragment() {
        return R.layout.fragment_event__aanvragen;
    }

    @Override
    protected int title() {return R.string.event_aanvragen;
    }
}