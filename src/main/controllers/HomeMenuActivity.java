package main.controllers;

import main.helper.IOHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.R;

public class HomeMenuActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_menu);
		setCustomActivityTitle("Seguimiento de Alumnos");
	}

	public void goToBuscarCursos(View v)
	{
		Intent intent = new Intent(this, BuscarCursosActivity.class);
		startActivity(intent);
	}

	public void goToABMCursos(View v)
	{
		Intent intent = new Intent(this, ABMCursoActivity.class);
		startActivity(intent);
	}

	public void dumpDBtoCSV(View v)
	{
		IOHelper ioh = new IOHelper(this);
		ioh.dumpDBtoCSV();
		Toast.makeText(getApplicationContext(), "Archivo generado correctamente", Toast.LENGTH_LONG).show();
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
