package com.example.heo.model;

import java.io.Serializable;



public class User implements Serializable {

	
	private static final long serialVersionUID = -7133623716019644997L;
	

	
	private String pseudo;
	private String mail;
	private String mdp;
	private String confirmMdp;

	public User() {
		super();
	}

	
	
	
	
	
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getConfirmMdp() {
		return confirmMdp;
	}

	public void setConfirmMdp(String confirmMdp) {
		this.confirmMdp = confirmMdp;
	}







	public String getPseudo() {
		return pseudo;
	}







	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	

} 

