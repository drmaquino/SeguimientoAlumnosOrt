package main.controllers;

import java.util.ArrayList;
import java.util.List;

import main.helper.DBHelper;
import main.model.Curso;
import main.model.Materia;
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

import com.app.R;



public class ListarMateriasActivity extends Activity {
	  	private ListView lvlMaterias;
	    private int id_curso;
	    private DBHelper dbh;

	  	 @Override
	     protected void onCreate(Bundle savedInstanceState)
	     {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.activity_listar_materias);
	        
	         lvlMaterias =(ListView)findViewById(R.id.ListaMaterias);
	         
	         dbh = new DBHelper(this);

	        //Traigo el curso -> me lo podria traer del intent !!!
	        id_curso = this.getIntent().getIntExtra("id_curso", 1);
	        Curso curso = dbh.findCursoById(id_curso);
	        // seteo el nombre del curso como titulo //
	        setCustomActivityTitle(curso.getNombreResumido());
	        
	        //traigo las Materias//
	        id_curso = this.getIntent().getIntExtra("id_curso", 1);
	        final List<Materia> MateriaLista = dbh.findMateriasByIdCurso(id_curso);
	        
	        List<String> materiaslist = new ArrayList<String>();
	        for (Materia materia : MateriaLista) {
				materiaslist.add(materia.getNombre().toString());
			}
	        
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materiaslist);
	        lvlMaterias.setAdapter(adapter);

	        lvlMaterias.setOnItemClickListener(new OnItemClickListener()
	        {
	            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	            {
	                Intent intent = new Intent(getApplicationContext(), ListarGruposActivity.class);
	               
	                Materia materia = dbh.findMateriaById(MateriaLista.get(position).getId());
	                intent.putExtra("id_materia",materia.getId());
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
