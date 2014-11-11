package main.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.app.R;

public class HomeActivity extends Activity
{
    private Spinner spAnios;
    private Spinner spCuatrimestres;
    private Spinner spCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spAnios = (Spinner) findViewById(R.id.spAnios);
        spCuatrimestres = (Spinner) findViewById(R.id.spCuatrimestres);
        spCursos = (Spinner) findViewById(R.id.spCursos);
    }
    
    public void buscar(View v)
    {
        String anio = spAnios.getSelectedItem().toString();
        String cuatri = spCuatrimestres.getSelectedItem().toString();
        String letra = spCursos.getSelectedItem().toString();
        Intent intent = new Intent(this, GruposActivity.class);

        // empaqueto y adjunto los datos del curso y se los mando a la prox
        // activity con el intent
        Bundle bundle = new Bundle();
        bundle.putStringArray("curso", new String[]
        { anio, cuatri, letra });
        intent.putExtras(bundle);

        startActivity(intent);
    }
}