package com.example.vendas;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class NovaVendaActivity extends Activity {
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
	}
	
	public void salvarClick(View v){
		
	}
}
