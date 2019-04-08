package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.bp3.service.models.Tag;
import com.example.bp3.service.repository.TagRepository;

import java.util.List;

/**
 * @author sven
 */
public class TagViewModel extends AndroidViewModel {

    public TagViewModel(@NonNull Application application) {
        super(application);
    }

    public void create(List<String> tags) {
        tags.forEach(tag -> {
            Tag t = new Tag(tag);
            TagRepository.getInstance().create(t);
        });
    }

    public LiveData<List<Tag>> getTags() {
        return TagRepository.getInstance().getTags();
    }
}
