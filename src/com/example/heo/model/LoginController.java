package com.example.heo.model;

import java.util.List;
import org.restlet.resource.ClientResource;
import android.util.Log;

public class LoginController {

	private ClientResource cr = new ClientResource(EngineConfiguration.gae_path
			+ "/rest/login");

	public LoginController() {
		EngineConfiguration.getInstance();
		//Log.v("LoginController()","I got instance"+EngineConfiguration.getInstance());
	}

	public void AddLogin(Login login) {

		LoginControllerInterface lci = cr
				.wrap(LoginControllerInterface.class);
		try {
			
			lci.AddLogin(login);
			//Log.v("Try de AddLogin","Login added");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Error", e.toString());
		}

	}

	public List<Login> getLogin() {

		LoginControllerInterface lci = cr
				.wrap(LoginControllerInterface.class);

		ContainerLogin content = lci.getLogin();

		return content.getLoginList();
	}

}
