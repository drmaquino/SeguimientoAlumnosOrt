package main.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.R;

public class HomeMenuActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_menu);
	}

	public void goToABMCursos(View v)
	{
		Intent intent = new Intent(this, ABMCursoActivity.class);
		startActivity(intent);
	}
}
