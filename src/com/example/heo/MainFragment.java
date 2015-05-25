package com.example.heo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;








import com.example.heo.model.Help;
import com.example.heo.model.HelpController;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
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

	private EditText titre;
	private EditText description;
	private ListView listView;
	Intent i;
	String pseudo;
	Help h;
	
	public static final int ID_NOTIFICATION = 1988;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.main_fragment, container, false);
		listView = (ListView) rootView.findViewById(R.id.listView1);
		setHasOptionsMenu(true);
		Bundle b = getActivity().getIntent().getExtras();
		pseudo = b.getString("pseudo");
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

	
	
	public void showDialogHelp(){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Help");
		LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.help_alert, null);

		titre = (EditText) v.findViewById(R.id.edit_titre);
		description = (EditText) v.findViewById(R.id.edit_besoin);

		builder.setView(v);

		builder.setPositiveButton(R.string.confirmation_deconnection, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// FIRE ZE MISSILES!
				new AddHelp().execute();
				new GetAllHelp().execute();
				
				createNotify();

			}
		});
		builder.setNegativeButton(R.string.annulation_deconnection, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// User cancelled the dialog
			}
		});

		builder.show();
	}
	
	
		
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		
		//return super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.action_settings:
			Log.v("+++","+++");
			showDialogHelp();	
			return true;
		case R.id.action_refresh:
			Log.v("Actualiser in dash","Je click comme un ouf");
			
			return true;
			

		default :
			return super.onOptionsItemSelected(item);
		}


	}
	
	private void createNotify(){

		NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);        
		Notification notification = new Notification(R.drawable.h77, pseudo+": Help me !", System.currentTimeMillis());  
		PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, new Intent(), 0);
		String titreNotification = "Notification HEO !";
		String texteNotification = titre.getText().toString();         
		notification.setLatestEventInfo(getActivity(), titreNotification, texteNotification, pendingIntent);
		notification.vibrate = new long[] {0,200,100,200,100,200};
		notificationManager.notify(ID_NOTIFICATION, notification);

	}


	private void cancelNotify(){
		NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(ID_NOTIFICATION);
	}
	
	
	

//	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
//		// Inflate the menu items for use in the action bar
//		
//		inflater.inflate(R.menu.main, menu);
//		 super.onCreateOptionsMenu(menu,inflater);
//	}
	
	
	private class AddHelp extends AsyncTask<Void, Void, Void>{


		public String affiche_date(){
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM HH:mm");
			String s = dateFormat1.format(gc.getTime());
			return s;
		}



		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				h = new Help();
				h.setTitre(titre.getText().toString());
				h.setDescription(description.getText().toString());
				h.setHeure(affiche_date());
				
				Intent i2 = new Intent(getActivity(), HelpActivity.class);
				i2.putExtra("titre",titre.getText().toString() );
				new HelpController().addHelp(h);	
			}catch(Exception e){
				Log.e("Error", e.toString());
			}
			return null;
		}

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
					list.add(h.getTitre()+"/"+h.getDescription()+"/"+h.getHeure()+"/"+h.getDescription());

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
