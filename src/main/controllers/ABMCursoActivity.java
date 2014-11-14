package main.controllers;

import java.util.ArrayList;
import java.util.List;

import main.helper.DBHelper;
import main.model.Curso;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.R;

public class ABMCursoActivity extends Activity
{
	private ListView lvCursos;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abm_curso);

		lvCursos = (ListView) findViewById(R.id.listaDeCursos);
		
		lvCursos.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Toast.makeText(getApplicationContext(), "mostrando opciones para curso " + position, Toast.LENGTH_SHORT).show();
			}
		});
		
		cargarCursos();		
	}

	@Override
	public void onResume()
	{
		super.onResume();
		cargarCursos();
	}
	
	private void cargarCursos()
	{
		DBHelper dbh = new DBHelper(this);
		List<Curso> cursosObjs = dbh.getAllCursos();

		List<String> cursos = new ArrayList<String>();
		for (Curso curso : cursosObjs)
		{
			cursos.add(curso.toString());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cursos);
		lvCursos.setAdapter(adapter);
	}

	public void goToAltaCurso(View v)
	{
		Intent intent = new Intent(this, AltaCursoActivity.class);
		startActivity(intent);
	}
}
