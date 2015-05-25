package com.example.heo;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.zip.Inflater;

import com.example.heo.model.Help;
import com.example.heo.model.HelpController;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class DashBord2 extends Activity{
	private String heure =null;
	private EditText titre;
	private EditText description;
	private ListView listView;
	private String pseudo;
	private String mainID;
	public static final int ID_NOTIFICATION = 1988;
	
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.dash_bord);
		listView = (ListView) findViewById(R.id.listView1);
		
		
		new getAllHelp().execute();

	}

	public void showDialogHelp(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Help");
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.help_alert, null);

		titre = (EditText) v.findViewById(R.id.edit_titre);
		description = (EditText) v.findViewById(R.id.edit_besoin);
		builder.setView(v);

		builder.setPositiveButton(R.string.confirmation_deconnection, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// FIRE ZE MISSILES!
				new AddHelp().execute();
				
				
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
		switch(item.getItemId()){
		case R.id.action_settings:
			showDialogHelp();	
			return true;
		default :
			return super.onOptionsItemSelected(item);
		}



	}

	public boolean onCreateOptionsMenu(Menu menu) {
		
		// recuperer le psuedo depuis l'intente envoyé par mainactivity
		Bundle b = getIntent().getExtras();
		
		pseudo = b.getString("pseudo");
		//mainID = b.getString("mainID");
		
		
		
		
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Bienvenue sur HEO");
		alertDialog.setMessage("Salut "+pseudo+" et bienvenue parmis nous");
		//alertDialog.setIcon(R.drawable.smile);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int which) {
		      // TODO Add your code for the button here.
		   }
		});
		// Set the Icon for the Dialog
		
		alertDialog.show();
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@SuppressLint("SimpleDateFormat")
	private class AddHelp extends AsyncTask<Void, Void, Void>{

		public String affiche_date(){
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
			String s = dateFormat1.format(gc.getTime());
			return s;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try{
				Help h = new Help();
				h.setTitre(titre.getText().toString());
				h.setDescription(description.getText().toString());
				h.setHeure(affiche_date());

				new HelpController().addHelp(h);	
			}catch(Exception e){
				Log.e("Error", e.toString());
			}
			return null;
		}


	}

	private class getAllHelp extends AsyncTask<Void, Void, List<String>>{

		private List<Help> helpList = null;
		private List<String> list = null;
		
		
		
		
		@Override
		protected List<String> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			list = new ArrayList<String>();


			try{
				helpList = new HelpController().getAllHelp();
				for(Help h: helpList){
					
					list.add(h.getHeure()+" :"+h.getTitre()+""+h.getDescription());
					
				}

			}catch(Exception e){
				Log.e("Error in activity", e.toString());
			}

			
			return list;
		}
		protected void onPostExecute(List<String> result) {
			listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),
					android.R.layout.simple_list_item_1, result));
			//listView.updateViewLayout(listView, null);
		}

	}
	
	/*  NOTIFICATION */
	
	private void createNotify(){
		
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);        
		Notification notification = new Notification(R.drawable.h77, pseudo+": Help me !", System.currentTimeMillis());  
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ActivityNotification.class), 0);
		String titreNotification = "Notification HEO !";
		String texteNotification = titre.getText().toString();         
		notification.setLatestEventInfo(this, titreNotification, texteNotification, pendingIntent);
		notification.vibrate = new long[] {0,200,100,200,100,200};
		notificationManager.notify(ID_NOTIFICATION, notification);
	}


	private void cancelNotify(){
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(ID_NOTIFICATION);
	}

	
	
	
	

}
