package main.controllers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.R;

public class HomeActivity extends Activity
{
    private Spinner spAnios;
    private Spinner spCuatrimestres;
    private Spinner spCursos;
    private TextView tvTitulo;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spAnios = (Spinner) findViewById(R.id.spAnios);
        spCuatrimestres = (Spinner) findViewById(R.id.spCuatrimestres);
        spCursos = (Spinner) findViewById(R.id.spCursos);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);

        testGrabandoArchivoEnSD();
        testCargandoArchivoDesdeSD();
        testCreandoBaseDeDatos();
        testInsertandoRegistroDePruebaEnDB();

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                buscar();
            }
        });
    }

    private void testGrabandoArchivoEnSD()
    {

        // ESTO NECESITA AGREGAR PERMISOS AL MANIFEST!!

        try
        {
            File myFile = new File("/storage/emulated/0/mysdfile.txt");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append("Titulo de Prueba Cargado desde la SD");
            myOutWriter.close();
            fOut.close();
        } catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void testCargandoArchivoDesdeSD()
    {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "mysdfile.txt");

        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null)
            {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        tvTitulo.setText(text);
    }

    private void testCreandoBaseDeDatos()
    {
        db = openOrCreateDatabase("stud.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        String qry = "create table if not exists stud (_id integer primary key autoincrement, nombre text, apellido text);";
        db.execSQL(qry);
    }

    private void testInsertandoRegistroDePruebaEnDB()
    {
        db = openOrCreateDatabase("stud.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        ContentValues record = new ContentValues();

        record.put("nombre", "mariano");
        record.put("apellido", "aquino");

        db.insert("stud", null, record);

        db.close();

    }

    private void buscar()
    {
        String anio = spAnios.getSelectedItem().toString();
        String cuatri = spCuatrimestres.getSelectedItem().toString();
        String letra = spCursos.getSelectedItem().toString();
        Intent intent = new Intent(this, GruposActivity.class);

        // empaqueto y adjunto los datos del curso y se los mando a la prox
        // activity con el intent
        Bundle bundle = new Bundle();
        bundle.putStringArray("curso", new String[]
        { anio, cuatri, letra });
        intent.putExtras(bundle);

        startActivity(intent);
    }
}