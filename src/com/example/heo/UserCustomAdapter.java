package com.example.heo;

import java.util.List;

import com.example.heo.model.Help;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserCustomAdapter extends BaseAdapter{
	
	private List<String> listUser;
	private Context context;
	private LayoutInflater mInflater;
	
	
	public UserCustomAdapter(Context context, List<String> listUser){
		this.context = context;
		this.listUser = listUser;
		mInflater = LayoutInflater.from(context);
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		RelativeLayout layoutItem;
		if(convertView == null){
			layoutItem = (RelativeLayout) mInflater.inflate(R.layout.list_item, parent, false);

			
		
		}else{
			layoutItem = (RelativeLayout) convertView;
			
		}
		//Typeface font = Typeface.create("sans-serif", Typeface.NORMAL);
		try{
		String [] recup = listUser.get(position).split("/");
		 
		TextView pseudo = (TextView) layoutItem.findViewById(R.id.title);
		TextView mail = (TextView) layoutItem.findViewById(R.id.pseudo_t);
		
		
		pseudo.setText(recup[0]);
		mail.setText(recup[1]);
		}catch(Exception e){}
		return layoutItem;
	}
	//renvoie le nombre d'item pr��sent dans la liste 
	public int getCount(){
		return listUser.size();
	}
	
	public Object getItem(int position){
		return listUser.get(position);
	}
	
	public long getItemId(int position){
		return position;
	}
}

