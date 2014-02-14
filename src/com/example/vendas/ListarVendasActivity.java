package com.example.vendas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;

public class ListarVendasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_vendas);
		
		ListView ltw = (ListView)findViewById(R.id.ltwVendas);	
		
		
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
		
		Cursor cursor = db.rawQuery("SELECT vendas._id, vendas.preco, produtos.nome, vendas.la, vendas.lo FROM vendas INNER JOIN produtos ON produtos._id = vendas.produto", null);
		
		String[] from = {"_id", "preco", "nome", "la", "lo"};
		int[] to = {R.id.txvLId, R.id.txvLPreco, R.id.txvLNome, R.id.txvLa, R.id.txvLo};
		
		SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.model_listar, cursor, from, to);
		
		ltw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					
			public void onItemClick(AdapterView<?> ad, View v, int position, long id){
				
				SQLiteCursor c = (SQLiteCursor)ad.getAdapter().getItem(position);
				Intent it = new Intent(getBaseContext(), MapShowActivity.class);
				it.putExtra("latitude", c.getDouble(c.getColumnIndex("la")));
				it.putExtra("longitude", c.getDouble(c.getColumnIndex("lo")));
				
				startActivity(it);
				
			}
			
		});
		
		ltw.setAdapter(ad);	
		
		db.close();
	}

}
