package com.example.heo.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.example.heo.model.User;

public interface UserControllerInterface {

	
	@Put
	public void AddUser (User user);
		
	@Get
	public Container getUsers();
	
	
}
