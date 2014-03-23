package com.example.fruityfashion.activities;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





import com.example.fruityfashion.R;
import com.example.fruityfashion.entities.Retailer;
import com.example.fruityfashion.entities.RetailerStores;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LocateActivity extends Activity {
	private List<RetailerStores> stores;
	private Retailer retail;
	private GoogleMap googleMap;
	private Marker marker;
	private HashMap<Integer, RetailerStores> mapRequest;
	private LocationManager locationManager;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.map_stores);
		initilizeMap();
		setUpMap();
		 //googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
		 retail= (Retailer) this.getIntent().getSerializableExtra("retailer");
		 stores=retail.getRetailerStores();
		for (int i = 0; i < stores.size(); i++) {
			
		    mapRequest = new HashMap<Integer, RetailerStores>();
			mapRequest.put(i, stores.get(i));
			googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(stores.get(i).getLatitude()),Double.parseDouble( stores.get(i).getLongitude()))
					
					).title(stores.get(i).getStoreAddress()));

		}
	
	/* private class CustomInfoWindowAdapter implements InfoWindowAdapter {
		 
	        private View infoview;
	 
	        public CustomInfoWindowAdapter() {
	            infoview = getLayoutInflater().inflate(R.layout.custom_info_window,
	                    null);
	        }

			@Override
			public View getInfoContents(Marker marker) {
				 if (LocateActivity.this.marker != null
		                    && LocateActivity.this.marker.isInfoWindowShown()) {
					 LocateActivity.this.marker.hideInfoWindow();
					 LocateActivity.this.marker.showInfoWindow();
					
		            }
		            return null;
				
			}

			@Override
			public View getInfoWindow(final Marker marker) {
				LocateActivity.this.marker = marker;
				final ImageView image = ((ImageView) infoview.findViewById(R.id.badge));
				 image.setImageResource(R.drawable.ic_launcher);
				// TODO Auto-generated method stub
				return null;
			}
	 }*/
	 googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {
	        @Override
	        public View getInfoWindow(Marker arg0) {
	            return null;
	        }
	        @Override
	        public View getInfoContents(Marker marker) {
	            View myContentView = getLayoutInflater().inflate(
	                    R.layout.custom_info_window,null);
	            TextView tvTitle = ((TextView) myContentView
	                    .findViewById(R.id.title));
	            tvTitle.setText(marker.getTitle());
	            ImageView image = ((ImageView) myContentView.findViewById(R.id.badge));
				 image.setImageResource(R.drawable.ic_launcher);
	            return myContentView;
	        }
	    });
	 btn_home = (ImageButton) findViewById(R.id.activity_map_ibtn_home);
	    btn_home.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            finish();
	        }
	    });
	}       
	private void initilizeMap() {
		Log.d("de", "de 1");
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			
		}
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	
	private Location setUpMap() {
		// Enable MyLocation Layer of Google Map
		Log.d("de", "de 2");
		googleMap.setMyLocationEnabled(true);

		// Get LocationManager object from System Service LOCATION_SERVICE
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Create a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Get the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Get Current Location
		android.location.Location driverLocation = locationManager
				.getLastKnownLocation(provider);

		// set map type
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// Get latitude of the current location
		double latitude = driverLocation.getLatitude();

		// Get longitude of the current location
		double longitude = driverLocation.getLongitude();

		// Create a LatLng object for the current location
		//this.myLocation = new Location(latitude, longitude);

		// Show the current location in Google Map
		googleMap.moveCamera(CameraUpdateFactory.newLatLng((new LatLng(
				latitude, longitude))));

		// Zoom in the Google Map
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
		// googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,
		// longitude)).title("driver").visible(false));
		// return latLng;

		//Log.d("driver", myLocation.getLatitude().toString());
		return null;
	}

	

}
