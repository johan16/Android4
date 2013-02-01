package com.example.proyectofotos;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class CamaraActivity extends Activity {
	
	private String foto = "";
	private static int TOMAR_FOTO = 1;
	private static int SELECCIONAR_FOTO = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camara);
		
		//asignar nombre a foto
		foto = Environment.getExternalStorageDirectory() + "/test.jpg";
		
		//Mapear los elementos
		RadioButton rbtnFoto = (RadioButton) this.findViewById(R.id.btnFoto);
		RadioButton rbtnGaleria = (RadioButton) this.findViewById(R.id.btnGaleria);
		
		Button btnCapturar = (Button) this.findViewById(R.id.btnCapturar);
		
		btnCapturar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_camara, menu);
		return true;
	}

}
