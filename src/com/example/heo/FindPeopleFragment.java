package com.example.heo;

import java.util.ArrayList;
import java.util.List;

import com.example.heo.model.User;
import com.example.heo.model.UserController;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FindPeopleFragment extends Fragment {
	
	private ListView listPeople;
	
	public FindPeopleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		//lister tout les users
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
        listPeople = (ListView) rootView.findViewById(R.id.listViewPeople);
        new GetAllUser().execute();
        
        return rootView;
    }
	
	private class GetAllUser extends AsyncTask<Void, Void, List<String>> {

		private List<User> UserList = null;
		private List<String> list = null;

		@Override
		protected List<String> doInBackground(Void... params) {

			list = new ArrayList<String>();

			try {

				UserList = new UserController().getUsers();
				for (User u : UserList) {

					list.add(u.getPseudo()+"/"+u.getMail());

				}

			} catch (Exception e) {
				Log.e("Error in getAllperson", e.toString());
			}

			return list;
		}
		protected void onPostExecute(List<String> result) {
			listPeople.setAdapter(new UserCustomAdapter(getActivity(),result));
			
		}
	
}
	
}
