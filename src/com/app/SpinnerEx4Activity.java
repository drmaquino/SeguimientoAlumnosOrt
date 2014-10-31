package com.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerEx4Activity extends Activity {

	Spinner s1, s2, s3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner_ex4);

		s1 = (Spinner) findViewById(R.id.spinner1);
		s2 = (Spinner) findViewById(R.id.spinner2);
		s3 = (Spinner) findViewById(R.id.spinner3);

		final Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String anio = s1.getSelectedItem().toString();
				String cuatri = s2.getSelectedItem().toString();
				String curso = s3.getSelectedItem().toString();
				CharSequence text = String.format("Buscando\n%s / %s / %s\n...", anio, cuatri, curso);
				Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
			}
		});
	}
}