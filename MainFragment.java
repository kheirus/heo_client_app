package com.example.heo;

import java.util.ArrayList;
import java.util.List;

import com.example.heo.model.Help;
import com.example.heo.model.HelpController;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainFragment extends Fragment{


	private ListView listView;
	Intent i;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.main_fragment, container, false);
		listView = (ListView) rootView.findViewById(R.id.listView1);


		new GetAllHelp().execute();
		

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.v("Click","j'ai cliker");
				TextView desc = (TextView) view.findViewById(R.id.description);
				TextView titre = (TextView) view.findViewById(R.id.title);
				i =new Intent(getActivity(), HelpActivity.class);
				i.putExtra("TITRE", titre.getText());
				i.putExtra("DESC",desc.getText());
				startActivity(i);
			}
		});

		return rootView;
	}

	public void refresh(){
		new GetAllHelp().execute();
	}

	
	
	
	class GetAllHelp extends AsyncTask<Void, Void, List<String>>{

		private List<Help> helpList = null;
		private List<String> list = null;

		@Override
		protected List<String> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			list = new ArrayList<String>();
			try{
				helpList = new HelpController().getAllHelp();
				for(Help h: helpList){
					list.add(h.getTitre()+"\n"+h.getDescription()+"\n"+h.getHeure());

				}
			}catch(Exception e){
				Log.e("Error in activity", e.toString());
			}
			return list;
		}
		protected void onPostExecute(List<String> result) {
			listView.setAdapter(new CustomAdapter(getActivity(),result));
			//listView.setAdapter(new CustomAdapter(getBaseContext(),listHelp));
		}


	}




}
