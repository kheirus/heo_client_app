package com.example.heo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
 
public class ActivityNotification extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dash_bord);
		
		
		TextView txt=new TextView(this);
		txt.setText("En phase d'experimentation");
 
		
		setContentView(txt);
 
		
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(DashBord2.ID_NOTIFICATION);
	}
}
