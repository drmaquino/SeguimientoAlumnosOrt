package main.controllers;

import java.util.ArrayList;

import main.helper.DBHelper;
import main.model.Trabajo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.R;

public class EstadosTPs extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ArrayList<Trabajo> ListaDeTrabajos = new ArrayList<Trabajo>();
		final ArrayList<TextView> ListaTextView = new ArrayList<TextView>();
		final ArrayList<Spinner> ListaSpiners = new ArrayList<Spinner>();
		setContentView(R.layout.activity_estadostp);
		
		int id_grupo = this.getIntent().getIntExtra("id_grupo", 0);

		DBHelper dbh = new DBHelper(getApplicationContext());

		//
		TextView txtTP1 = (TextView) findViewById(R.id.textView1);
		TextView txtTP2 = (TextView) findViewById(R.id.textView2);
		TextView txtTP3 = (TextView) findViewById(R.id.textView3);
		TextView txtTP4 = (TextView) findViewById(R.id.textView4);
		TextView txtTP5 = (TextView) findViewById(R.id.textView5);
		TextView txtTP6 = (TextView) findViewById(R.id.textView6);
		TextView txtTP7 = (TextView) findViewById(R.id.textView7);
		
		ListaTextView.add(txtTP1);
		ListaTextView.add(txtTP2);
		ListaTextView.add(txtTP3);
		ListaTextView.add(txtTP4);
		ListaTextView.add(txtTP5);
		ListaTextView.add(txtTP6);
		ListaTextView.add(txtTP7);

		//
		Button btnGuardar = (Button) findViewById(R.id.GuardarEstados);

		//
		Spinner spnEstados1 = (Spinner) findViewById(R.id.spinner1);
		Spinner spnEstados2 = (Spinner) findViewById(R.id.spinner2);
		Spinner spnEstados3 = (Spinner) findViewById(R.id.spinner3);
		Spinner spnEstados4 = (Spinner) findViewById(R.id.spinner4);
		Spinner spnEstados5 = (Spinner) findViewById(R.id.spinner5);
		Spinner spnEstados6 = (Spinner) findViewById(R.id.spinner6);
		Spinner spnEstados7 = (Spinner) findViewById(R.id.spinner7);
		
		ListaSpiners.add(spnEstados1);
		ListaSpiners.add(spnEstados2);
		ListaSpiners.add(spnEstados3);
		ListaSpiners.add(spnEstados4);
		ListaSpiners.add(spnEstados5);
		ListaSpiners.add(spnEstados6);
		ListaSpiners.add(spnEstados7);

		//
		Trabajo trabajo1 = new Trabajo(1, "TP 1", "Pendiente");
		Trabajo trabajo2 = new Trabajo(1, "TP 2", "Pendiente");
		Trabajo trabajo3 = new Trabajo(1, "TP 3", "Pendiente");
		Trabajo trabajo4 = new Trabajo(1, "TP 4", "Pendiente");
		Trabajo trabajo5 = new Trabajo(1, "TP 5", "Pendiente");
		Trabajo trabajo6 = new Trabajo(1, "TP 6", "Pendiente");
		Trabajo trabajo7 = new Trabajo(1, "TP 7", "Pendiente");

		dbh.addTrabajo(trabajo1);
		dbh.addTrabajo(trabajo2);
		dbh.addTrabajo(trabajo3);
		dbh.addTrabajo(trabajo4);
		dbh.addTrabajo(trabajo5);
		dbh.addTrabajo(trabajo6);
		dbh.addTrabajo(trabajo7);

		//
		ListaDeTrabajos = (ArrayList<Trabajo>) dbh.findTrabajosByIdGrupo(id_grupo);

		//
		
		int i = 0;
		for (Trabajo trabajo : ListaDeTrabajos)
		{
			ListaTextView.get(i).setText(trabajo.get_nombre());
			i++;
		}
		
		i = 0;
		for (Trabajo trabajo : ListaDeTrabajos)
		{
			if (trabajo.get_estado().equals("Aprobado"))
			{
				ListaSpiners.get(i).setSelection(0);
			}
			else if (trabajo.get_estado().equals("Pendiente"))
			{
				ListaSpiners.get(i).setSelection(1);
			}
			else if (trabajo.get_estado().equals("Desaprobado"))
			{
				ListaSpiners.get(i).setSelection(2);
			}
			else if (trabajo.get_estado().equals("Entregado"))
			{
				ListaSpiners.get(i).setSelection(3);
			}
			i++;
		}

		btnGuardar.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DBHelper db = new DBHelper(getApplicationContext());

				int i = 0;
				for (Spinner spinner : ListaSpiners)
				{
					Trabajo trabajo = new Trabajo();
					trabajo = db.getTrabajoById((String) ListaTextView.get(i).getText());

					trabajo.set_estado(ListaSpiners.get(i).getSelectedItem().toString());
					db.updateTrabajo(trabajo);
					i++;
				}

			}
		});

	}

}
