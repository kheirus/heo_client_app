package com.example.heo.model;


import java.util.ArrayList;
import java.util.List;

import com.example.heo.model.Login;


public class ContainerLogin {

	public List<Login> loginList;

	public List<Login> getLoginList() {

		return loginList;
	}

	public void setLoginList(List<Login> loginList) {

		this.loginList = loginList;
	}

	public ContainerLogin() {

		loginList = new ArrayList<Login>();
	}

	public ContainerLogin(List<Login> loginList) {

		this.loginList = loginList;
	}

}

