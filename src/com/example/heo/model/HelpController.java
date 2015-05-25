package com.example.heo.model;

import java.util.List;

import org.restlet.resource.ClientResource;



import android.util.Log;

public class HelpController {
	
	private ClientResource cr = new ClientResource(
			EngineConfiguration.gae_path+"/rest/help");
	
	
	public HelpController() {
		EngineConfiguration.getInstance();
	}
	
	public void addHelp(Help help){
		HelpControllerInterface hci = cr.wrap(HelpControllerInterface.class);
		
		try{
			hci.addHelp(help);
		}catch(Exception e){
			Log.e("Error",e.toString());
		}
	}

	public List<Help> getAllHelp(){
		HelpControllerInterface uci = cr.wrap(HelpControllerInterface.class);
		
		ContainerHelp content = uci.getHelp();
		
		return content.getHelpList();
	}
}
