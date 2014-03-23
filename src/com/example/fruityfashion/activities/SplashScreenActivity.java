package com.example.fruityfashion.activities;


import java.io.ByteArrayOutputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.widget.ImageView;

import com.example.fruityfashion.R;
import com.example.fruityfashion.entities.RetailerInfoResponse;
import com.example.fruityfashion.utils.RequestHandler;
import com.google.gson.Gson;


public class SplashScreenActivity extends FragmentActivity{
	public static final long SPLASH_DISPLAY_LENGHT = 3000;
	private static final String URL = "http://223.25.237.175/appwizlive/getRetailerInfo.php";
    private static final String RETAILER_ID="MerchantA1";
    private static final int PRIVATE_MODE = 0;         
    private static final String PREF_NAME = "appwizRetail";    	 // Sharedpref file name
	private RetailerInfoResponse retailerInfoResponse;
	private JSONObject json;
	private ObjectMapper mapper;
	private Bitmap bitmap;
	private ImageView splash;
	private Bitmap splashbitmap;
	private Bitmap logo;
	private SharedPreferences pref;   
	private Editor editor;    
	private Gson gson;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_splash);
		splash=(ImageView )findViewById(R.id.imgLogo);
		retailerInfoResponse=new RetailerInfoResponse();
		new ServerRead().execute("");
	}
	
	public void getInfo(){
		try {
			json=new JSONObject();
			json.put("retailerId", RETAILER_ID);
			String url =URL;
			mapper = new ObjectMapper();
			retailerInfoResponse = mapper.readValue(RequestHandler.getInstance().connect("POST", url, json)
							.getResponse(), RetailerInfoResponse.class
					);
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setView(){
		splash.setImageBitmap(splashbitmap);
	}
	
	public Bitmap getImage(String imageurl){
	      bitmap=BitmapFactory.decodeStream(RequestHandler.getInstance().connectImage("GET", imageurl));
	      	return bitmap;
	      	        }
	
	 public String encodeTobase64(Bitmap image) {
         Bitmap immage = image;
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
         byte[] b = baos.toByteArray();
         String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
         return imageEncoded;
     }
		private class ServerRead extends AsyncTask<String, Void, String> {
	        
			@Override
	        protected String doInBackground(String... params) {
			  getInfo();
	          splashbitmap=getImage(retailerInfoResponse.getData().getSplashImage().toString());
	          logo=getImage(retailerInfoResponse.getData().getCompanyLogo().toString());
	            return null;
	        }
	       
	        @Override
	        protected void onPostExecute(String result) {
	        	setView();
	             gson= new Gson();
	 			pref = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
				editor = pref.edit(); 	
				editor.putString("Logo",encodeTobase64(logo));
		        editor.putString("MyObject", gson.toJson(retailerInfoResponse));
		        editor.commit(); 
	        	
	        	
	        	new Handler().postDelayed(new Runnable(){
	                @Override
	                public void run() {
	                    /* Create an Intent that will start the Menu-Activity. */
	                    Intent mainIntent = new Intent(SplashScreenActivity.this,HomeScreenActivity.class);
	                    startActivity(mainIntent);
	                    finish();
	                }
	            }, SPLASH_DISPLAY_LENGHT);
	        }
	        

	        @Override
	        protected void onPreExecute() {
	        }

	        @Override
	        protected void onProgressUpdate(Void... values) {
	        }
	    }
		
}
