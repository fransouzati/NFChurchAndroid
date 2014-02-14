package com.example.vendas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
		
		StringBuilder sqlProdutos = new StringBuilder();
		sqlProdutos.append("CREATE TABLE IF NOT EXISTS [produtos](");
		sqlProdutos.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sqlProdutos.append("nome varchar(100),");
		sqlProdutos.append("preco DOUBLE(10,2));");
		db.execSQL(sqlProdutos.toString());
		
		db.execSQL("DELETE FROM produtos");
		
		db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Coca Cola', '2.50')");
		db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Red Bull', '6.50')");
		
		StringBuilder sqlVendas = new StringBuilder();
		sqlVendas.append("CREATE TABLE IF NOT EXISTS [vendas](");
		sqlVendas.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sqlVendas.append("produto INTEGER,");
		sqlVendas.append("preco DOUBLE(10,2), ");
		sqlVendas.append("la DOUBLE(10,9), ");
		sqlVendas.append("lo DOUBLE(10,9));");
		db.execSQL(sqlVendas.toString());
		
		db.close();
	}
	
	public void NovaVendaClick(View v){
		startActivity(new Intent(getBaseContext(), NovaVendaActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
