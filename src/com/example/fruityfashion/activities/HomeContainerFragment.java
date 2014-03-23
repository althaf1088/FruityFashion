package com.example.fruityfashion.activities;





import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.fruityfashion.R;
import com.example.fruityfashion.entities.RetailerInfoResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.gson.Gson;




public class HomeContainerFragment extends Fragment{
    private static final int PRIVATE_MODE = 0;         
    private static final String PREF_NAME = "appwizRetail";
    
	View view;
	VideoView videoView;
	GoogleMap map;
	ImageButton overflow;
	RetailerInfoResponse retailerInfoResponse;
	//RetailRequest request;
	public FragmentTabHost mTabHost1;
	RelativeLayout navBar;
	TextView navTitle;
	TextView myTitleText;
	Context context;
	JSONObject json;
	Bitmap bitmap;
	ImageView logo;
	Bitmap logoBitmao;
	SharedPreferences pref;   
    Editor editor;  
    Gson gson = new Gson();
	private Bitmap logobitmap;
	
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		view = inflater.inflate(R.layout.tab_retailerinfo, container,false);
		context=this.getActivity();
		navBar = (RelativeLayout) view.findViewById(R.id.nav_bar);
		navTitle = (TextView) view.findViewById(R.id.nav_title);
		logo=(ImageView)view.findViewById(R.id.imageView1);
		mTabHost1 = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);
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
	 pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
	 String json = pref.getString("MyObject", "");
	 String logoimage=pref.getString("Logo", "");
     retailerInfoResponse = gson.fromJson(json,RetailerInfoResponse.class);
     logobitmap=decodeBase64(logoimage);
     setview();
	
     
     mButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(context, LocateActivity.class);
			intent.putExtra("retailer", retailerInfoResponse.getData());
			
			startActivity(intent);
			
		}
	});
		return view;
				
	}
	
	 @SuppressWarnings("deprecation")
	public void setview(){
		 getActivity().setTitle(retailerInfoResponse.getData().getRetailerName());
		 getActivity().setTitleColor(Color.parseColor("#"+retailerInfoResponse.getData().getRetailerTextColor().toString()));
		 getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#"+retailerInfoResponse.getData().getHeaderColor().toString())));
	     logo.setImageBitmap(logobitmap); 
		 if(retailerInfoResponse.getData().getBackdropType().equalsIgnoreCase("color")){
			 setBackgroundColor();
			 }
		 else{
			 setBackgroundImage();
		 }
		  
	 }
	 
	 
	 @SuppressWarnings("deprecation")
	public void setBackgroundColor(){
		/*int h = view.getHeight();
		    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
		    mDrawable.getPaint().setShader(new LinearGradient(0, 0, 0, h, Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor1().toString()), Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor2().toString()), TileMode.CLAMP));
		    view.setBackgroundDrawable(mDrawable);*/
		 view.setBackgroundDrawable( new DrawableGradient(new int[] { Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor1().toString()), Color.parseColor("#"+retailerInfoResponse.getData().getBackdropColor2().toString()) }, 0).SetTransparency(10));
	 }
	 public class DrawableGradient extends GradientDrawable {
	        DrawableGradient(int[] colors, int cornerRadius) {
	            super(GradientDrawable.Orientation.TOP_BOTTOM, colors);

	            try {
	                this.setShape(GradientDrawable.RECTANGLE);
	                this.setGradientType(GradientDrawable.LINEAR_GRADIENT);
	                this.setCornerRadius(cornerRadius);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        public DrawableGradient SetTransparency(int transparencyPercent) {
	            this.setAlpha(255 - ((255 * transparencyPercent) / 100));

	            return this;
	        }
	    }
	 public void setBackgroundImage(){
		 //Call get image url to fetch image and set it here	
		    
	 }
	
	
	 public  Bitmap decodeBase64(String input) {
	        byte[] decodedByte = Base64.decode(input, 0);
	        return BitmapFactory
	                .decodeByteArray(decodedByte, 0, decodedByte.length);
	    }
	
	      	
	      	        
}
