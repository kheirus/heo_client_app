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

public class CustomAdapter extends BaseAdapter{
	
	private List<String> listHelp;
	private Context context;
	private LayoutInflater mInflater;
	private Button comment;
	
	public CustomAdapter(Context context, List<String> listHelp){
		this.context = context;
		this.listHelp = listHelp;
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
		String [] recup = listHelp.get(position).split("/");
		TextView title = (TextView) layoutItem.findViewById(R.id.title);
		TextView description = (TextView) layoutItem.findViewById(R.id.description);
		TextView heure = (TextView)layoutItem.findViewById(R.id.duration);
		TextView ps = (TextView)layoutItem.findViewById(R.id.pseudo_t);
		
		//String descSplit = description.getText().toString();
		try{
		String [] recupDescSplit = recup[1].split(" ");
		String s = recupDescSplit[0]+" "+recupDescSplit[1]+" "+recupDescSplit[2]+"...";
		
		
		
		title.setText(recup[0]);
		description.setText(recup[1]);
		heure.setText(recup[2]);
		ps.setText(s);
		}catch(Exception e){ }
		//title.setTypeface(font);
		return layoutItem;
	}
	//renvoie le nombre d'item pr��sent dans la liste 
	public int getCount(){
		return listHelp.size();
	}
	
	public Object getItem(int position){
		return listHelp.get(position);
	}
	
	public long getItemId(int position){
		return position;
	}
}

