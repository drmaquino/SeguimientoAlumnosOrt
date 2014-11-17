package main.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.app.R;

public class BuscarCursosActivity extends Activity
{
    private Spinner spAnios;
    private Spinner spCuatrimestres;
    private Spinner spCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cursos);

        spAnios = (Spinner) findViewById(R.id.spAnios);
        spCuatrimestres = (Spinner) findViewById(R.id.spCuatrimestres);
        spCursos = (Spinner) findViewById(R.id.spCursos);
    }

    public void buscar(View v)
    {
        String anio = spAnios.getSelectedItem().toString();
        String cuatrimestre = spCuatrimestres.getSelectedItem().toString();
        String letra = spCursos.getSelectedItem().toString();

        // empaqueto los datos del curso
        Bundle bundle = new Bundle();
        bundle.putStringArray("curso", new String[] { anio, cuatrimestre, letra });

        // creo un intent y le los adjunto los datos
        Intent intent = new Intent(this, ListarGruposActivity.class);
        intent.putExtras(bundle);

        // inicio el intent
        startActivity(intent);
    }
}