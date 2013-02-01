package com.example.proyectofotos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;

public class CamaraActivity extends Activity {
	
	private String foto = "";
	private static int TOMAR_FOTO = 1;
	private static int SELECCIONAR_FOTO = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camara);
		
		//asignar nombre a foto
		Calendar cal = Calendar.getInstance();
		int annio = cal.get(Calendar.YEAR);
		int segundo = cal.get(Calendar.SECOND);
		
		//pendiente para agregar nombre de archivo
		
		foto = Environment.getExternalStorageDirectory() + "/"+annio+segundo+".jpg";
		
		//Mapear los elementos
		final RadioGroup rbGroup =  (RadioGroup) this.findViewById(R.id.rbGroup);
		final RadioButton rbtnFoto = (RadioButton) this.findViewById(R.id.btnFoto);
		final RadioButton rbtnGaleria = (RadioButton) this.findViewById(R.id.btnGaleria);
		
		Button btnCapturar = (Button) this.findViewById(R.id.btnCapturar);
		
		
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
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_camara, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		
		//PROCESO PARA TOMAR UNA FOTO
		if(requestCode == TOMAR_FOTO){
			//saber si los datos son integros
			if(data != null){
				//si el archivo es una imagen
				if(data.hasExtra("data")){
					//mapear image view
					ImageView imgPreview = (ImageView) this.findViewById(R.id.imgPreview);
					//cargar preview
					//( asignar la foto)
					imgPreview.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
				}
			}
			// data null
			else{
				//mapear image view
				ImageView imgPreview = (ImageView) this.findViewById(R.id.imgPreview);
				//obtener la imagen desde ruta especifica
				imgPreview.setImageBitmap(BitmapFactory.decodeFile(foto));
				
				//obtener foto desde la camara
				new MediaScannerConnectionClient(){
					private MediaScannerConnection msc = null;
					{
						
						msc = new MediaScannerConnection( getApplicationContext(), this);
						msc.connect();
					}
					@Override
					public void onMediaScannerConnected() {
						// TODO Auto-generated method stub
						msc.scanFile(foto, null);
					}

					@Override
					public void onScanCompleted(String path, Uri uri) {
						// TODO Auto-generated method stub
						msc.disconnect();
					}
					
				};
			}
		}
		else if(requestCode == SELECCIONAR_FOTO){
			Uri imagen = data.getData();
			//carga la foto x fluta
			InputStream is;
			try{
				is = getContentResolver().openInputStream(imagen);
				BufferedInputStream bis = new BufferedInputStream(is);
				Bitmap bitmap = BitmapFactory.decodeStream(bis);
				//mapear image view
				ImageView imgPreview = (ImageView) this.findViewById(R.id.imgPreview);
				
				imgPreview.setImageBitmap(bitmap);
				
			}catch(Exception ex){
				//mostrar mensaje de error;
	

			};
		}

	}



}
