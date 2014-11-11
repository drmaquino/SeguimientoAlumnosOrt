package main.controllers;

import java.util.ArrayList;
import java.util.List;

import main.model.Curso;
import main.model.Grupo;
import main.persistence.CursoDAO;
import main.persistence.GrupoDAO;
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

        Bundle bundle = this.getIntent().getExtras();
        String[] datosDelCurso = bundle.getStringArray("curso");

        CursoDAO cdao = new CursoDAO(this);
        Curso curso = cdao.findCursoByAnioCuatriLetra(datosDelCurso[0], datosDelCurso[1], datosDelCurso[2]);

        tvCurso = (TextView) findViewById(R.id.curso);
        lvGrupos = (ListView) findViewById(R.id.listaDeCursos);

        tvCurso.setText(curso.toString());

        GrupoDAO gdao = new GrupoDAO(this);
        gdao.regenerateDB();
        Grupo g1 = new Grupo(0, 0, "Grupo 1");
        Grupo g2 = new Grupo(1, 0, "Grupo 2");
        Grupo g3 = new Grupo(2, 0, "Grupo 3");
        Grupo g4 = new Grupo(3, 1, "Grupo 4");
        Grupo g5 = new Grupo(4, 0, "Grupo 5");
        Grupo g6 = new Grupo(5, 0, "Grupo 6");
        gdao.addGrupo(g1);
        gdao.addGrupo(g2);
        gdao.addGrupo(g3);
        gdao.addGrupo(g4);
        gdao.addGrupo(g5);
        gdao.addGrupo(g6);

        List<Grupo> gruposObjs = gdao.findGruposByIdCurso(0);
        List<String> grupos = new ArrayList<String>();
        for (Grupo g : gruposObjs)
        {
            grupos.add(g.get_numero());
        }

        // String[] grupos = new String[] { "Grupo 1", "Grupo 2", "Grupo 3",
        // "Grupo 4", "Grupo 5", "Grupo 6", "Grupo 7" };

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
