package com.nfservice.nfchurch;

import java.util.Arrays;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class FragmentMembros extends Fragment {
	
   ListView listaMembros;
   ArrayAdapter<String> adapter;
   EditText buscaMembro;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	  View view = inflater.inflate(R.layout.fragment_membros, container, false);
	  listaMembros = (ListView) view.findViewById(R.id.listaMembros);
	  buscaMembro = (EditText) view.findViewById(R.id.buscaMembro);		
		
	  String[] membros = getResources().getStringArray(R.array.membros);
	   
	  adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_membro, R.id.membro, membros);
	  listaMembros.setAdapter(adapter);
	  
	  buscaMembro.addTextChangedListener(new TextWatcher() {		     
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
			    // When user changed the Text
			    adapter.getFilter().filter(cs);   
			}
			 
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			        int arg3) {
			    // TODO Auto-generated method stub
			     
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
	  });
	  
	  return view;
  } 
  

}