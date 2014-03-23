package com.example.fruityfashion.activities;





import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Shader.TileMode;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.fruityfashion.R;
import com.example.fruityfashion.entities.RetailRequest;
import com.example.fruityfashion.entities.RetailerInfoResponse;
import com.example.fruityfashion.utils.RequestHandler;
import com.google.android.gms.maps.GoogleMap;




public class HomeContainerFragment extends Fragment{
	View view;
	VideoView videoView;
	GoogleMap map;
	ImageButton overflow;
	RetailerInfoResponse retailerInfoResponse;
	RetailRequest request;
	private ObjectMapper mapper;
	//Retailer stores = null;
	public FragmentTabHost mTabHost1;
	RelativeLayout navBar;
	TextView navTitle;
	TextView myTitleText;
	Context context;
	JSONObject json;
	Bitmap bitmap;
	ImageView logo;
	
	
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		view = inflater.inflate(R.layout.tab_retailerinfo, container,false);
		
		context=this.getActivity();
		navBar = (RelativeLayout) view.findViewById(R.id.nav_bar);
		navTitle = (TextView) view.findViewById(R.id.nav_title);
		logo=(ImageView)view.findViewById(R.id.imageView1);
		mTabHost1 = (FragmentTabHost) getActivity().findViewById(
				android.R.id.tabhost);
		mTabHost1.setVisibility(View.VISIBLE);
		Button mButton = (Button) view.findViewById(R.id.locate);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context, LocateActivity.class);
				intent.putExtra("retailer", retailerInfoResponse.getData());
				
				startActivity(intent);
				
			}
		});
	    
		
	retailerInfoResponse=new RetailerInfoResponse();
	new ServerRead().execute("");
	mButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(context, LocateActivity.class);
			intent.putExtra("retailer", retailerInfoResponse.getData());
			
			startActivity(intent);
			
		}
	});
	
		 //TextView myTitleText = (TextView) view.findViewById(R.id.nav_title);
		 //this.getActivity().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_custom_titlebar);
		return view;
				
	}
	private void setcustomtitlebar() {
		// TODO Auto-generated method stub
		
		//this.getActivity().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_custom_titlebar);
	    
		
	       //  myTitleText = (TextView) this.getActivity().getWindow().findViewById(R.id.myTitle);
	        
	    
		
	}
	
	 @SuppressWarnings("deprecation")
	public void setview(){
		 getActivity().setTitle(retailerInfoResponse.getData().getRetailerName());
		 getActivity().setTitleColor(Color.parseColor("#"+retailerInfoResponse.getData().getRetailerTextColor().toString()));
	     //view.setBackgroundColor(Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor1().toString()));
		 int h = view.getHeight();
		    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
		    mDrawable.getPaint().setShader(new LinearGradient(0, 0, 0, h, Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor1().toString()), Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor2().toString()), TileMode.CLAMP));
		    view.setBackgroundDrawable(mDrawable);
		 
		 getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#"+retailerInfoResponse.getData().getHeaderColor().toString())));
	     logo.setImageBitmap(bitmap);  
	 }
	 
	public void getInfo(){
		try {
			json=new JSONObject();
			json.put("retailerId", "MerchantA1");
			String url = "http://223.25.237.175/appwizlive/getRetailerInfo.php";
			mapper = new ObjectMapper();
			retailerInfoResponse = mapper.readValue(
					RequestHandler.getInstance().connect("POST", url, json)
							.getResponse(), RetailerInfoResponse.class
					);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getImage(){
		 String imageurl=retailerInfoResponse.getData().getCompanyLogo().toString();
	      bitmap=BitmapFactory.decodeStream(RequestHandler.getInstance().connectImage("GET", imageurl));
	      	
	      	        }
	private class ServerRead extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
          getInfo();
          getImage();
					
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        	
        	setview();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
	
	
}
