package com.example.bp3.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Sven
 */

public abstract class ViewFragment extends Fragment {

    protected int lf;
    protected int ttl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.lf = this.layoutFragment();
        this.ttl = this.title();
        return inflater.inflate(lf, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(ttl);
    }

    /**
     *
     * @return R.layout.[element]
     */
    protected abstract int layoutFragment();

    /**
     *
     * @return R.string.[element]
     */
    protected abstract int title();

}
