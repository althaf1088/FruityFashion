package com.example.fruityfashion.activities;


import java.lang.reflect.Field;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruityfashion.R;

public class HomeScreenActivity extends FragmentActivity {
	public FragmentTabHost mTabHost;
	Menu mainMenu=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   
		/*final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_home);
		
		if ( customTitleSupported ) {
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_custom_titlebar);
	    }*/
		setContentView(R.layout.activity_home);
		ActionBar actionBar = getActionBar();
		// Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
	  
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("HOME").setIndicator("HOME"),
                HomeContainerFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("E-SHOP").setIndicator("E-SHOP"),
                EShopContainerFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("LOYALTY").setIndicator("LOYALTY"),
                LoyaltyContainerFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("FEEDBACK")
                .setIndicator("FEEDBACK"), FeedbackContainerFragment.class,
                null);
        mTabHost.addTab(mTabHost.newTabSpec("VOUCHERS")
                .setIndicator("VOUCHERS"), VouchersContainerFragment.class,
                null);
	
	}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.menu, menu);
	        return super.onCreateOptionsMenu(menu);
	    }
	 
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	 
	        super.onOptionsItemSelected(item);
	 
	        switch(item.getItemId()){
	           
	        }
	        return true;
	 
	    }
	
}
