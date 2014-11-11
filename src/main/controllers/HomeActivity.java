package main.controllers;

import main.model.Curso;
import main.persistence.CursoDAO;
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

        /* TEST: guardo el curso para que la DB no este vacia en la prox pantalla */
        CursoDAO cdao = new CursoDAO(this);
        cdao.regenerateDB();
        Curso curso = new Curso();
        curso.set_anio(anio);
        curso.set_cuatrimestre(cuatri);
        curso.set_letra(letra);
        cdao.addCurso(curso);
        /**/

        // empaqueto los datos del curso
        Bundle bundle = new Bundle();
        bundle.putStringArray("curso", new String[] { anio, cuatri, letra });

        // creo un intent y le los adjunto los datos
        Intent intent = new Intent(this, GruposActivity.class);
        intent.putExtras(bundle);

        // inicio el intent
        startActivity(intent);
    }
}