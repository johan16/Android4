package com.example.proyectofotos;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
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
		final RadioButton rbtnFoto = (RadioButton) this.findViewById(R.id.btnFoto);
		final RadioButton rbtnGaleria = (RadioButton) this.findViewById(R.id.btnGaleria);
		
		Button btnCapturar = (Button) this.findViewById(R.id.btnCapturar);
		
		btnCapturar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				int code = TOMAR_FOTO;
				
				//si el usuario selecciona la camara
				if(rbtnFoto.isChecked()){
					//Lanzar la camara
					Uri output = Uri.fromFile(new File(foto));
					//aki se va crear la imagen
					i.putExtra(MediaStore.EXTRA_OUTPUT, output);
				}else if(rbtnGaleria.isChecked()){
					
				}
				
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
