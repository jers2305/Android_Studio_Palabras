package com.example.palabrasroom;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraRepositorio {
    private  PalabraDAO mPalabraDAO;
    private LiveData<List<Palabra>> mPalabra;

    PalabraRepositorio(Application application){
        PalabraDB db = PalabraDB.getDatabase(application);
        mPalabraDAO= db.palabraDAO();
        mPalabra = mPalabraDAO.getPalabrasOrdenadas();
    }

    LiveData<List<Palabra>> getAllPalabras(){
        return mPalabra;
    }
    void  insert(Palabra palabra){
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.insert(palabra);
        });
    }
    public void delete(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.delete(palabra);
        });
    }

}
