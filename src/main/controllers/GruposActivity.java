package main.controllers;

import java.util.ArrayList;
import java.util.List;

import main.helper.DBHelper;
import main.model.Curso;
import main.model.Grupo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.R;

public class GruposActivity extends Activity
{
    private ListView lvGrupos;
    private TextView tvCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        tvCurso = (TextView) findViewById(R.id.curso);
        lvGrupos = (ListView) findViewById(R.id.listaDeCursos);

        Bundle bundle = this.getIntent().getExtras();
        String[] datosDelCurso = bundle.getStringArray("curso");

        String anio = datosDelCurso[0];
        String cuatrimestre = datosDelCurso[1];
        String letra = datosDelCurso[2];

        /* creo un curso */
        Curso cursoIn = new Curso();
        cursoIn.set_anio(anio);
        cursoIn.set_cuatrimestre(cuatrimestre);
        cursoIn.set_letra(letra);

        /* creo varios grupos */
        Grupo g1 = new Grupo(0, 0, "Grupo 1");
        Grupo g2 = new Grupo(1, 0, "Grupo 2");
        Grupo g3 = new Grupo(2, 0, "Grupo 3");
        Grupo g4 = new Grupo(3, 1, "Grupo 4");
        Grupo g5 = new Grupo(4, 0, "Grupo 5");
        Grupo g6 = new Grupo(5, 0, "Grupo 6");

        /* creo el db helper */
        DBHelper dbh = new DBHelper(this);

        /* guardo el curso */
        dbh.addCurso(cursoIn);

        /* guardo los grupos */
        dbh.addGrupo(g1);
        dbh.addGrupo(g2);
        dbh.addGrupo(g3);
        dbh.addGrupo(g4);
        dbh.addGrupo(g5);
        dbh.addGrupo(g6);

        /* traigo el curso */
        Curso cursoOut = dbh.findCursoByAnioCuatriLetra(anio, cuatrimestre, letra);

        /* traigo los grupos */
        List<Grupo> gruposObjs = dbh.findGruposByIdCurso(0);

        tvCurso.setText(cursoOut.toString());

        List<String> grupos = new ArrayList<String>();
        for (Grupo g : gruposObjs)
        {
            grupos.add(g.get_numero());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, grupos);
        lvGrupos.setAdapter(adapter);

        lvGrupos.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                Toast.makeText(getApplicationContext(), "entrando al grupo!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
