package com.example.heo;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.heo.model.Help;
import com.example.heo.model.HelpController;
import com.example.heo.model.NavDrawerItem;








import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.ViewDebug.IntToString;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;





@SuppressLint("Recycle")
public class DashBord3 extends Activity{
	private ListView listView;
	private EditText titre;
	private EditText description;
	private TextView heure;
	MainFragment mainFragment;
	private List<Help> listHelp;
	private String pseudo;

	/*Elements pour le slidingMenu*/
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	/*fin slidingmeni*/
	public static final int ID_NOTIFICATION = 1988;
	
	Help h;
	protected void onCreate(Bundle savedInstance){

		super.onCreate(savedInstance);
		setContentView(R.layout.dash_bord);
		listView = (ListView)findViewById(R.id.listView1);
		
		listHelp = new ArrayList<Help>();
		Bundle b = getIntent().getExtras();
		pseudo = b.getString("pseudo");
		
//		
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Bienvenue sur HEO");
		alertDialog.setMessage("Salut "+pseudo+" et bienvenue parmi nous");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Add your code for the button here.
			}
		});
		// Set the Icon for the Dialog

		alertDialog.show();
//
//		
//		new GetAllHelp().execute();
//
		
		/***For sliding menue***/

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		
		
		navDrawerItems = new ArrayList<NavDrawerItem>();
        
		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

		navMenuIcons.recycle();
		Log.v("before listener","oo");
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		Log.v("afeter listener","oo");

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.drawer_open, // nav drawer open - description for accessibility
				R.string.drawer_close// nav drawer close - description for accessibility
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
				Log.v("ferme","...");
			}

			public void onDrawerOpened(View drawerView) {
				//getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
				Log.v("ouvert","..");
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);

//		if (savedInstance == null) {
//			// on first time display view for first nav item
//			displayView(0);
//		}

		/***end sliding menu***/



		








	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
		
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			//Log.v("Sliding Listener ",getString(position));
			displayView(position);
		}
	}
	
	private void displayView(int position) {
		// update the main content by replacing fragments
		
			
		Fragment fragment = null;
		switch (position) {
		case 0:
			Log.v("touche home","");
			fragment = new MainFragment();
			break;
		case 1:
			fragment = new FindPeopleFragment();
			break;
		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
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
		//		new GetAllHelp().execute();
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

	
	
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
		    return true;
		}
		
		//return super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.action_settings:
			showDialogHelp();	
			return true;
		case R.id.action_refresh:
			Log.v("Actualiser in dash","Je click comme un ouf");
//			mainFragment = (MainFragment)getFragmentManager().findFragmentById(R.id.drawer_layout);
//			mainFragment.refresh();
			return true;
			

		default :
			return super.onOptionsItemSelected(item);
		}


	}

	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
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

				new HelpController().addHelp(h);	
			}catch(Exception e){
				Log.e("Error", e.toString());
			}
			return null;
		}

	}

	


	private void createNotify(){

		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);        
		Notification notification = new Notification(R.drawable.h77, pseudo+": Help me !", System.currentTimeMillis());  
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
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
