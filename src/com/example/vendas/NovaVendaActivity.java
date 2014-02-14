package com.example.vendas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NovaVendaActivity extends Activity implements LocationListener {
	
	private double la;
	private double lo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_venda);
		
		Spinner spProdutos = (Spinner)findViewById(R.id.spProdutos);
		
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
		
		Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome ASC", null);
		
		String[] from = {"_id", "nome", "preco"};
		int[] to = {R.id.txvId, R.id.txvNome, R.id.txvPreco};
		
		SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.spinner, cursor, from, to);
		
		spProdutos.setAdapter(ad);	
		
		db.close();
	}
	
	public void salvarClick(View v){
		Location location = null;
		LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        // Pega status do GPS
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Pega status da internet
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnabled || isNetworkEnabled) {
        	// se tiver gps, pega localidade, senão, pega localidade da internet
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            1,
                            10, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            } else if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 10, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }            
        }
		
		if (location != null) {
			la = location.getLatitude();
			lo = location.getLongitude();
		}
		
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
		
		Spinner spProdutos = (Spinner)findViewById(R.id.spProdutos);
		
		SQLiteCursor dados = (SQLiteCursor)spProdutos.getAdapter().getItem(spProdutos.getSelectedItemPosition());
		
		ContentValues ctv = new ContentValues();
		ctv.put("produto", dados.getInt(0));
		ctv.put("preco", dados.getDouble(2));
		ctv.put("la", la);
		ctv.put("lo", lo);
		
		db.insert("vendas", "_id", ctv);
		
		Toast.makeText(getBaseContext(), "Venda cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
		
		db.close();
	}

	@Override
	public void onLocationChanged(Location location) {
		//la = location.getLatitude();
		//lo = location.getLongitude();
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
