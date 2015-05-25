package com.example.heo;



import java.util.ArrayList;
import java.util.List;

import com.example.heo.R;
import com.example.heo.AddUsers;
import com.example.heo.model.ContainerLogin;
import com.example.heo.model.User;
import com.example.heo.model.UserController;
import com.example.heo.model.Login;
import com.example.heo.model.LoginController;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	Intent i = null;
	private static List<String> list = null;
	private EditText txtLog;
	private EditText txtMdp;
	
	private String conn;
	private boolean ok;
	

	protected void onCreate(Bundle savedInstanceState) {
		ok=false;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new getAllLogin().execute();
		
		Button connexion = (Button) findViewById(R.id.connexion);
		Button inscription = (Button) findViewById(R.id.inscription);

		inscription.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, AddUsers.class);
				startActivity(i);
			}		

		});

		connexion.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {



				txtLog =(EditText) findViewById(R.id.login);
				txtMdp =(EditText) findViewById(R.id.mot_de_passe);
				conn = txtLog.getText().toString()+txtMdp.getText().toString();

				for (String cur : list){

					Log.v("LISTE LOGIN : ",cur);
					if (cur.contains(conn) && !conn.equals(""))
					{
						ok=true;
						break;
					}
				}

				if (ok){
					Intent i = new Intent(MainActivity.this, DashBord3.class);
					Intent i2 = new Intent(MainActivity.this, MainFragment.class);
					i.putExtra("pseudo",txtLog.getText().toString() );
					i2.putExtra("pseudo", txtLog.getText().toString());
					startActivity(i);
					finish();
				}

				else{
					Toast toast = Toast.makeText(getApplicationContext(), "Erreur login!", Toast.LENGTH_SHORT);
					toast.show();
					toast.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL, 0, 0);
					Log.e("ERREUR LOGIN",conn);
				}
			}

		});





	}



	private class getAllLogin extends AsyncTask<Void, Void, List<String>> {

		private List<Login> LoginList = null;


		@Override
		protected List<String> doInBackground(Void... params) {

			list = new ArrayList<String>();

			try {

				LoginList = new LoginController().getLogin();
				for (Login l : LoginList) {

					list.add(l.getLogin()  + l.getMdp());

				}

			} catch (Exception e) {
				Log.e("Error in getAllperson", e.toString());
			}

			return list;
		}


	}







}