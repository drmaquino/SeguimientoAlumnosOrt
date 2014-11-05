package main.controllers;

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

public class GruposActivity extends Activity {
	private ListView lvGrupos;
	private TextView tvCurso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grupos);

		Bundle bundle = this.getIntent().getExtras();
		String[] datosDelCurso = bundle.getStringArray("curso");

		tvCurso = (TextView) findViewById(R.id.curso);
		lvGrupos = (ListView) findViewById(R.id.listaDeCursos);

		tvCurso.setText(String.format("%s, %s, %s", datosDelCurso[0],
				datosDelCurso[1], datosDelCurso[2]));

		String[] grupos = new String[] { "Grupo 1", "Grupo 2", "Grupo 3",
				"Grupo 4", "Grupo 5", "Grupo 6", "Grupo 7" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, grupos);
		lvGrupos.setAdapter(adapter);

		lvGrupos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				Toast.makeText(getApplicationContext(), "entrando al grupo!",
						Toast.LENGTH_LONG).show();
			}
		});
	}
}
