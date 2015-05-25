package com.example.heo.model;

import java.util.List;
import org.restlet.resource.ClientResource;
import android.util.Log;

public class UserController {

	private ClientResource cr = new ClientResource(EngineConfiguration.gae_path
			+ "/rest/user");

	public UserController() {
		EngineConfiguration.getInstance();
		//Log.v("UserController()","I got instance"+EngineConfiguration.getInstance());
	}

	public void AddUser(User user) {

		UserControllerInterface uci = cr
				.wrap(UserControllerInterface.class);
		try {
			
			uci.AddUser(user);
			//Log.v("Try de AddUser","User added");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Error", e.toString());
		}

	}

	public List<User> getUsers() {

		UserControllerInterface pci = cr
				.wrap(UserControllerInterface.class);

		Container content = pci.getUsers();

		return content.getUserList();
	}

}
