package com.example.palabrasroom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PalabraViewModel palabraViewModel;
    private PalabraRVAdppter palabraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView palabraRecycleView = findViewById(R.id.Palabra);
        palabraRecycleView.setLayoutManager(new LinearLayoutManager(this));
        palabraAdapter = new PalabraRVAdppter(new PalabraRVAdppter.WordDiff(), this);
        palabraRecycleView.setAdapter(palabraAdapter);

        palabraViewModel = new ViewModelProvider(this).get(PalabraViewModel.class);

        palabraViewModel.getPalabra().observe(this, palabras -> {
            palabraAdapter.submitList(palabras);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> showAddPalabraDialog());
    }

    private void showAddPalabraDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_palabra, null);
        EditText editTextPalabra = dialogView.findViewById(R.id.edit_text_palabra);

        new AlertDialog.Builder(this)
                .setTitle("Agregar Palabra")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialogInterface, i) -> {
                    String palabra = editTextPalabra.getText().toString();
                    if (!palabra.trim().isEmpty()) {
                        palabraViewModel.insert(new Palabra(palabra));
                        Toast.makeText(this, "Palabra agregada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Por favor, ingresa una palabra", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.dismiss())
                .create()
                .show();
    }
}
