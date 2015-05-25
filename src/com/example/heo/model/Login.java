package com.example.heo.model;


import java.io.Serializable;




public class Login implements Serializable {

	private static final long serialVersionUID = 8603021666915928818L;
	

	private String login;
	private String mdp;

	public Login(){}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
}
