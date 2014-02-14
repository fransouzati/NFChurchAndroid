package com.example.vendas;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MapShowActivity extends Activity {
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		//GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapa_view)).getMap();
		
		GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapa_view)).getMap();
		
		//map.setBuiltInZoomControls(true);
		//map.displayZoomControls(true);
		
		Intent it = getIntent();
		int latitude = (int)(it.getDoubleExtra("latitude", 0)*1E6);
		int longitude = (int)(it.getDoubleExtra("longitude", 0)*1E6);
		
		LatLng latlng = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 30));
		
		//MapController mc = map.getController();
		
		//mc.animateTo(new GeoPoint(latitude, longitude));		
		//mc.setZoom(30);
		//map.invalidate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_show, menu);
		return true;
	}

}
