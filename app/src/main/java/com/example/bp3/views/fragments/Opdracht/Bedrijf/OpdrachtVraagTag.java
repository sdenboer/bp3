package com.example.bp3.views.fragments.Opdracht.Bedrijf;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.example.bp3.R;
import com.example.bp3.service.models.Tag;
import com.example.bp3.viewmodels.OpdrachtViewModel;
import com.example.bp3.viewmodels.TagViewModel;
import com.example.bp3.views.adapters.OpdrachtBijTagAdapter;
import com.example.bp3.views.adapters.TagSearchAdapter;
import com.example.bp3.views.fragmentsHelpers.ViewFragment;

import java.util.ArrayList;

/**
 * @author sven
 */
public class OpdrachtVraagTag extends ViewFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_opdracht_tags, container, false);
        TagViewModel tvm = ViewModelProviders.of(this).get(TagViewModel.class);
        OpdrachtViewModel ovm = ViewModelProviders.of(this).get(OpdrachtViewModel.class);
        AutoCompleteTextView editText = root.findViewById(R.id.tag_search);
        ImageButton btn = root.findViewById(R.id.tag_search_btn);
        TagSearchAdapter tagSearchAdapter = new TagSearchAdapter(this.getContext(), new ArrayList<>());
        tvm.getTags().observe(this, tagSearchAdapter::setTags);
        editText.setAdapter(tagSearchAdapter);

        RecyclerView recyclerView = root.findViewById(R.id.tags_vraag_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        OpdrachtBijTagAdapter opdrachtBijTagAdapter = new OpdrachtBijTagAdapter(this.getActivity());
        recyclerView.setAdapter(opdrachtBijTagAdapter);
        btn.setOnClickListener(e -> {
            Tag tag = new Tag(editText.getText().toString());
            ovm.getByTags(tag).observe(this, opdrachtBijTagAdapter::setOpdrachten);
        });
        return root;
    }

    @Override
    public int title() {
        return R.string.projecten_opdrachten;
    }
}
