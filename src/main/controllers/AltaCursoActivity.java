package main.controllers;

import main.helper.DBHelper;
import main.model.Curso;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.R;

public class AltaCursoActivity extends Activity
{
    private Spinner spAnios;
    private Spinner spCuatrimestres;
    private Spinner spCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_curso);

        spAnios = (Spinner) findViewById(R.id.spAnios);
        spCuatrimestres = (Spinner) findViewById(R.id.spCuatrimestres);
        spCursos = (Spinner) findViewById(R.id.spCursos);
    }

    public void altaCurso(View v)
    {
        DBHelper dbh = new DBHelper(this);

        String anio = spAnios.getSelectedItem().toString();
        String cuatrimestre = spCuatrimestres.getSelectedItem().toString();
        String letra = spCursos.getSelectedItem().toString();

        Curso cursoTmp = dbh.findCursoByAnioCuatriLetra(anio, cuatrimestre, letra);

        int cant;

        if (cursoTmp == null)
        {
            Curso curso = new Curso();
            curso.set_anio(anio);
            curso.set_cuatrimestre(cuatrimestre);
            curso.set_letra(letra);
            dbh.addCurso(curso);
            cant = dbh.getCursosCount();
            Toast.makeText(this, "Curso creado. nueva cantidad: " + cant, Toast.LENGTH_LONG).show();
        }
        else
        {
            cant = dbh.getCursosCount();
            Toast.makeText(this, "El curso ya existe. sigue habiendo " + cant + " cursos.", Toast.LENGTH_LONG).show();
        }
    }
}
