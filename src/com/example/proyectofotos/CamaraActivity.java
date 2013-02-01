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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

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
		final RadioGroup rbGroup =  (RadioGroup) this.findViewById(R.id.rbGroup);
		final RadioButton rbtnFoto = (RadioButton) this.findViewById(R.id.btnFoto);
		final RadioButton rbtnGaleria = (RadioButton) this.findViewById(R.id.btnGaleria);
		
		//Button btnCapturar = (Button) this.findViewById(R.id.btnCapturar);
		
		
		rbGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		            @Override
		            public void onCheckedChanged(RadioGroup group, int checkedId) { 
		            	// TODO Auto-generated method stub
						Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						int code = TOMAR_FOTO;
						
						//SI EL USUARIO SELECCIONA LA CAMARA
						if(rbtnFoto.isChecked()){
							//Lanzar la camara
							Uri output = Uri.fromFile(new File(foto));
							//aki se va crear la imagen
							i.putExtra(MediaStore.EXTRA_OUTPUT, output);
						
						}//SI EL USUARIO SELECCIONA LA GALERIA
						else if(rbtnGaleria.isChecked()){
							//tomar desde la galaeria
							i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
							code = SELECCIONAR_FOTO;
						}
						
						//EJECUTAR ACTIVITY CUANDO USUARIO REALICE ACCION
						//(intent , y el codigo de la accion)
						startActivityForResult(i, code);
						
		            }
		});
		/*
		btnCapturar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				int code = TOMAR_FOTO;
				
				//SI EL USUARIO SELECCIONA LA CAMARA
				if(rbtnFoto.isChecked()){
					//Lanzar la camara
					Uri output = Uri.fromFile(new File(foto));
					//aki se va crear la imagen
					i.putExtra(MediaStore.EXTRA_OUTPUT, output);
				
				}//SI EL USUARIO SELECCIONA LA GALERIA
				else if(rbtnGaleria.isChecked()){
					//tomar desde la galaeria
					i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
					code = SELECCIONAR_FOTO;
				}
				
				//EJECUTAR ACTIVITY CUANDO USUARIO REALICE ACCION
				//(intent , y el codigo de la accion)
				startActivityForResult(i, code);
				
			}
			
		});
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_camara, menu);
		return true;
	}

}
