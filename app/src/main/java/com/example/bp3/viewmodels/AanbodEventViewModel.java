package com.example.bp3.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.bp3.service.models.AanbodEvent;
import com.example.bp3.service.repository.EventRepository;
import java.io.Serializable;
import java.util.List;

/**
 * @author Koen Franken
 */

public class AanbodEventViewModel extends AndroidViewModel implements Serializable {

    public AanbodEvent aanbodEvent;

    public AanbodEventViewModel(Application application) {
        super(application);
    }

    public AanbodEvent getAanbodEvent() {
        return aanbodEvent;
    }

    public void setAanbodEvent(AanbodEvent aanbodEvent) {
        this.aanbodEvent = aanbodEvent;
    }
    public LiveData<List<AanbodEvent>> getAllAanbodevent() {
        return EventRepository.getInstance().getAllAanbodevent();
    }

    public void create(AanbodEvent aanbodEvent) {
        EventRepository.getInstance().create(aanbodEvent);
    }

    public void update(AanbodEvent aanbodEvent) {
        EventRepository.getInstance().update(aanbodEvent);
    }

    public void delete(AanbodEvent aanbodEvent) {
        EventRepository.getInstance().delete(aanbodEvent);
    }
}