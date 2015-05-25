package com.example.heo.model;


import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.example.heo.model.Login;



public interface LoginControllerInterface {

	
	@Put
	public void AddLogin (Login l);
		
	@Get
	public ContainerLogin getLogin();
	
	
}
