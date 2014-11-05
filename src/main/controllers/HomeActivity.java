package main.controllers;

import main.model.Curso;

import com.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                buscar();
            }
        });
    }
    
    private void buscar()
    {
        String anio = spAnios.getSelectedItem().toString();
        String cuatri = spCuatrimestres.getSelectedItem().toString();
        String letra = spCursos.getSelectedItem().toString();
        Intent intent = new Intent(this, GruposActivity.class);
        
        //empaqueto y adjunto los datos del curso y se los mando a la prox activity con el intent
        Bundle bundle = new Bundle();
        bundle.putStringArray("curso", new String[]{anio, cuatri, letra});
        intent.putExtras(bundle);
        
        startActivity(intent);
    }
}