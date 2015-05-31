package com.example.pickphotofromgallery;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
	static final int REQUEST_IMAGE_GET = 1;
	ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void getPhoto (View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	    intent.setType("image/*");
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(intent, REQUEST_IMAGE_GET);
	    }
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
	        Bitmap thumbnail = data.getParcelableExtra("data");
	        Uri fullPhotoUri = data.getData();
	        // Do work with photo saved at fullPhotoUri
	        Log.d("DEB","fullPhotoUri= "+fullPhotoUri.toString());
	        
	        //this doesnot work, dont know why
	        //mImageView.setImageBitmap(thumbnail);
	        // following one works;
	        mImageView.setImageURI(fullPhotoUri);
	    }
	}
}
