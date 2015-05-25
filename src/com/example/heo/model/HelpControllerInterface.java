package com.example.heo.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface HelpControllerInterface {

	@Put
	public void addHelp(Help help);
	
	@Get
	public ContainerHelp getHelp();
}
