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

public class ListarGruposActivity extends Activity
{
    private ListView lvGrupos;
    private TextView tvCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_grupos);

        tvCurso = (TextView) findViewById(R.id.curso);
        lvGrupos = (ListView) findViewById(R.id.listaDeCursos);

        Bundle bundle = this.getIntent().getExtras();
        String[] datosDelCurso = bundle.getStringArray("curso");

        String anio = datosDelCurso[0];
        String cuatrimestre = datosDelCurso[1];
        String letra = datosDelCurso[2];

        /* creo el db helper */
        DBHelper dbh = new DBHelper(this);

        /* traigo el curso */
        Curso curso = dbh.findCursoByAnioCuatriLetra(anio, cuatrimestre, letra);

        // si el curso no existe, cierro la actividad y vuelvo a la pag de busqueda
        if (curso == null)
        {
            Toast.makeText(getApplicationContext(), "El curso seleccionado no existe", Toast.LENGTH_SHORT).show();
            super.finish();
        }
        else
        {
            /* seteo el nombre del curso como titulo */
            tvCurso.setText(curso.toString());

            /* traigo los grupos */
            List<Grupo> gruposObjs = dbh.findGruposByIdCurso(curso.get_id());

            tvCurso.setText(curso.toString());

            List<String> grupos = new ArrayList<String>();
            for (Grupo g : gruposObjs)
            {
                grupos.add(g.get_numero());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, grupos);
            lvGrupos.setAdapter(adapter);

            lvGrupos.setOnItemClickListener(new OnItemClickListener()
            {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                {
                    Toast.makeText(getApplicationContext(), "mostrando los TPs del grupo!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
