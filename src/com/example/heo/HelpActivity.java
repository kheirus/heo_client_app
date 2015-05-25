package com.example.heo;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.example.heo.R;
import com.example.heo.model.Comment;
import com.example.heo.model.CommentController;
import com.example.heo.model.Help;
import com.example.heo.model.HelpController;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class HelpActivity extends Activity{
		EditText d_coms;
		TextView textViewDesc;
		TextView textViewTitre;
		Comment c;
		String t;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_activity);
		textViewDesc = (TextView)findViewById(R.id.description_help_activity);
		textViewTitre = (TextView)findViewById(R.id.titre_help_activity);
		//buttonAddHelp = (Button) findViewById(R.id.button_add_help);
		Intent i = getIntent();
		String desc = i.getStringExtra("DESC");
		String titre = i.getStringExtra("TITRE");
		Log.v("INTENT", desc);
		textViewTitre.setText(titre);
		textViewDesc.setText(desc);
		
		Button helpMe = (Button) findViewById(R.id.do_help);
		helpMe.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialogComs();
			}
		});
		
	}
	
	public void showDialogComs(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Aidez moi !");
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.coms_alert, null);

		 d_coms= (EditText) v.findViewById(R.id.com_s);
		
		 builder.setView(v);

		builder.setPositiveButton(R.string.confirmation_deconnection, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				new addComment().execute();
				

			}
		});
		builder.setNegativeButton(R.string.annulation_deconnection, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				
			}
		});

		builder.show();
	}
	
	
	private class addComment extends AsyncTask<Void, Void, Void>{


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
				c = new Comment();
				c.setComs(d_coms.getText().toString());
				
				c.setHeure(affiche_date());
				 
				Bundle b = getIntent().getExtras();
				t = b.getString("titre");
				
				c.setParent(t);

				new CommentController().addComment(c);	
				Log.v("coms rajoute",d_coms.getText().toString());
			}catch(Exception e){
				Log.e("Error", e.toString());
			}
			return null;
		}

	}
	

}
