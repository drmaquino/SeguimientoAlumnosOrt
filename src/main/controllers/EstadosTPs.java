package main.controllers;

import java.util.ArrayList;
import java.util.List;

import main.helper.DBHelper;
import main.model.Trabajo;

import com.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class EstadosTPs extends Activity{
	

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ArrayList<Trabajo>ListaDeTrabajos = new ArrayList<Trabajo>();
        final ArrayList<TextView> ListaTextView = new ArrayList<TextView>();
        setContentView(R.layout.activity_estadostp);
        
        
        DBHelper db = new DBHelper(getApplicationContext());
        
    	TextView txtTP1= (TextView)findViewById(R.id.textView1);
    	TextView txtTP2= (TextView)findViewById(R.id.textView2);
    	TextView txtTP3= (TextView)findViewById(R.id.textView3);
    	TextView txtTP4= (TextView)findViewById(R.id.textView4);
    	TextView txtTP5= (TextView)findViewById(R.id.textView5);
    	TextView txtTP6= (TextView)findViewById(R.id.textView6);
    	TextView txtTP7= (TextView)findViewById(R.id.textView7);
    	Button btnGuardar = (Button)findViewById(R.id.GuardarEstados);
    	
    	
		Spinner spnEstados1 = (Spinner)findViewById(R.id.spinner1);
		Spinner spnEstados2 = (Spinner)findViewById(R.id.spinner2);
		Spinner spnEstados3 = (Spinner)findViewById(R.id.spinner3);
		Spinner spnEstados4 = (Spinner)findViewById(R.id.spinner4);
		Spinner spnEstados5 = (Spinner)findViewById(R.id.spinner5);
		Spinner spnEstados6 = (Spinner)findViewById(R.id.spinner6);
		Spinner spnEstados7 = (Spinner)findViewById(R.id.spinner7);
		
		final ArrayList<Spinner> ListaSpiners = new ArrayList<Spinner>();
		
		ListaSpiners.add(spnEstados1);
		ListaSpiners.add(spnEstados2);
		ListaSpiners.add(spnEstados3);
		ListaSpiners.add(spnEstados4);
		ListaSpiners.add(spnEstados5);
		ListaSpiners.add(spnEstados6);
		ListaSpiners.add(spnEstados7);

        
         Trabajo trabajo1 = new Trabajo(001,"FINAL1","GRU1","Pendiente");
         Trabajo trabajo2 = new Trabajo(002,"FINAL2","GRU1","Pendiente");
         Trabajo trabajo3 = new Trabajo(003,"FINAL3","GRU1","Pendiente");
         Trabajo trabajo4 = new Trabajo(004,"FINAL4","GRU1","Pendiente");
         Trabajo trabajo5 = new Trabajo(005,"FINAL5","GRU1","Pendiente");
         Trabajo trabajo6 = new Trabajo(006,"FINAL6","GRU1","Pendiente");
         Trabajo trabajo7 = new Trabajo(007,"FINAL7","GRU1","Pendiente");
         
         
         db.addTrabajo(trabajo1);
         db.addTrabajo(trabajo2);
         db.addTrabajo(trabajo3);
         db.addTrabajo(trabajo4);
         db.addTrabajo(trabajo5);
         db.addTrabajo(trabajo6);
         db.addTrabajo(trabajo7);
         
         ListaTextView.add(txtTP1);
         ListaTextView.add(txtTP2);
         ListaTextView.add(txtTP3);
         ListaTextView.add(txtTP4);
         ListaTextView.add(txtTP5);
         ListaTextView.add(txtTP6);
         ListaTextView.add(txtTP7);
         
         
         ListaDeTrabajos = (ArrayList<Trabajo>) db.getAllTrabajosByIdGrupo("GRU1");
         
         int i =0;
         for (Trabajo trabajo : ListaDeTrabajos) {
        	ListaTextView.get(i).setText(trabajo.get_id_trabajo());
        	i++;
			
		}
         String gg;
         i =0;
         for (Trabajo trabajo : ListaDeTrabajos) {
        	 gg=trabajo.get_estado();
        	 if (trabajo.get_estado().equals("Aprobado")){
        		 ListaSpiners.get(i).setSelection(0);
        	 }else if (trabajo.get_estado().equals("Pendiente")){
        		 ListaSpiners.get(i).setSelection(1);
        	 }else if (trabajo.get_estado().equals("Desaprobado")){
        		 ListaSpiners.get(i).setSelection(2);
        	 }else if (trabajo.get_estado().equals("Entregado")){
        		 ListaSpiners.get(i).setSelection(3);
        	 }
        	 
			
		}
         
         
         btnGuardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DBHelper db = new DBHelper(getApplicationContext());
				
				int i =0;
				for (Spinner spinner : ListaSpiners) {
					Trabajo trabajo = new Trabajo();
					 trabajo = db.getTrabajoById((String)ListaTextView.get(i).getText());
					 
					 trabajo.set_estado(ListaSpiners.get(i).getSelectedItem().toString());
					db.updateTrabajo(trabajo);
					i++;
				}
				
			}
		});
         
        
   }

}
