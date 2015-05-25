package com.example.heo;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



public class TextFragment extends Fragment{
	public static final String ARG = "text_fragment";
	
	public TextFragment(){}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.text_fragment, container, false);
		int i = getArguments().getInt(ARG);
		//String photo = getResources().getStringArray(R.array.db)[i];
		//getActivity().setTitle(photo);
		
		return rootView;
		
		
	}
}