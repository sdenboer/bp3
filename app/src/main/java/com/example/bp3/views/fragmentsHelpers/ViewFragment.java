package com.example.bp3.views.fragmentsHelpers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @author Sven
 */

public abstract class ViewFragment extends Fragment implements FragmentInterface {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(this.title());
    }

}
