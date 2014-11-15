package main.controllers;

import com.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

public class EstadosTPs extends Activity{

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadostp);
        Toast.makeText(getApplicationContext(), "Entroo", Toast.LENGTH_SHORT).show();

    }
	
}
