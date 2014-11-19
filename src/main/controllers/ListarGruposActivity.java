package main.controllers;

import java.util.ArrayList;
import java.util.List;

import main.helper.DBHelper;
import main.model.Curso;
import main.model.Grupo;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
    private int id_curso;
    private DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_grupos);
        
        setCustomActivityTitle("Lista de Grupos");

        tvCurso = (TextView) findViewById(R.id.curso);
        lvGrupos = (ListView) findViewById(R.id.listaDeCursos);

        dbh = new DBHelper(this);

        /* traigo el curso */
        id_curso = this.getIntent().getIntExtra("id_curso", 1);
        Curso curso = dbh.findCursoById(id_curso);

        /* seteo el nombre del curso como titulo */
        tvCurso.setText(curso.toString());

        /* traigo los grupos */
        List<Grupo> gruposObjs = dbh.findGruposByIdCurso(curso.get_id());

        tvCurso.setText(curso.toString());

        List<String> grupos = new ArrayList<String>();
        for (Grupo g : gruposObjs)
        {
            grupos.add("Grupo " + g.get_numero());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, grupos);
        lvGrupos.setAdapter(adapter);

        lvGrupos.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), ListarTrabajosActivity.class);
                Grupo grupo = dbh.findGrupoByIdCursoNumero(id_curso, String.valueOf(position + 1));
                intent.putExtra("id_grupo", grupo.get_id());
                startActivity(intent);
            }
        });
    }
    
	private void setCustomActivityTitle(String title)
	{
		ActionBar ab = getActionBar();
	    ab.setDisplayShowTitleEnabled(false);
	    ab.setDisplayShowCustomEnabled(true);
	    View customTitle = getLayoutInflater().inflate(R.layout.activity_titles, null);
	    TextView tv = (TextView) customTitle.findViewById(R.id.title);
	    tv.setText(title);
		ab.setCustomView(customTitle);
	}
}
