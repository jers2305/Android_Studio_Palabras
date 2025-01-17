package com.example.palabrasroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraViewModel  extends AndroidViewModel {
    private PalabraRepositorio mPalabraRepository;
    private final LiveData<List<Palabra>> mPalabra;

    public PalabraViewModel(@NonNull Application application) {
        super(application);
        mPalabraRepository = new PalabraRepositorio(application);
        mPalabra = mPalabraRepository.getAllPalabras();
    }
    public void delete(Palabra  palabra) {
        mPalabraRepository.delete(palabra);
    }

    LiveData<List<Palabra>> getPalabra(){
        return mPalabra;
    }

    public void insert (Palabra palabra){
        mPalabraRepository.insert(palabra);
    }



}