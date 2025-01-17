package com.example.palabrasroom;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PalabraDAO {

    //insertar fila en la tabla y se si se repite la clave el conflicto es omitido y no se insertara la fila
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Palabra palabra);

    // query ejecuta la consulta SQL directa
    @Query("DELETE FROM tabla_palabra")
    void deleteAll();

    @Query("SELECT * FROM  tabla_palabra ORDER BY palabra ASC")
    LiveData<List<Palabra>> getPalabrasOrdenadas();

    @Delete
    void delete(Palabra palabra);

}