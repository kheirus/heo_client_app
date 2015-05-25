package com.example.heo;



import java.util.ArrayList;
import java.util.List;

import com.example.heo.model.User;
import com.example.heo.model.UserController;
import com.example.heo.model.Login;
import com.example.heo.model.LoginController;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class AddUsers extends Activity{
	public static final String ID = "id";
	Intent i = null;

	
	private EditText txtPseudo; 
	private EditText txtEmail;
	private EditText txtMdp;
	private EditText txtConfirmMdp;
	private ListView listView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.add_users);



		
		txtPseudo = (EditText) findViewById(R.id.userPseudo);
		txtEmail = (EditText) findViewById(R.id.userMail);
		txtMdp = (EditText) findViewById(R.id.userPassword);
		txtConfirmMdp = (EditText) findViewById(R.id.userConfirmPassword);

		
		Button creerCompte = (Button) findViewById(R.id.createAccount);


		creerCompte.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
			
				new AddPerson().execute();
			
				i = new Intent(AddUsers.this, DashBord3.class);
				i.putExtra("pseudo",txtPseudo.getText().toString() );
				
				startActivity(i);



			}
		});

	}
	
	


	
	
	
	
	private class AddPerson extends AsyncTask<Void, Void, Void> {


		protected Void doInBackground(Void... params) {

			try {

				User u = new User();
				Login l = new Login();
				
				//On rentre le nouvel utilisateur
				
				u.setPseudo(txtPseudo.getText().toString());
				u.setMail(txtEmail.getText().toString());
				u.setMdp(txtMdp.getText().toString());
				u.setConfirmMdp(txtConfirmMdp.getText().toString());
				
				//on l'insere aussi dans la table login 
				l.setLogin(txtPseudo.getText().toString());
				l.setMdp(txtConfirmMdp.getText().toString());
				new UserController().AddUser(u);
				new LoginController().AddLogin(l);
				

			} catch (Exception e) {
				//Log.e("Ici le String :"+txtNom.getText().toString(), "string string ");
				Log.e("Erreur ici", e.toString());
			}
			return null;
		}
	}









	


}
