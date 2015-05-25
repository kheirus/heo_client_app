package com.example.heo.model;

import java.util.ArrayList;
import java.util.List;
import com.example.heo.model.User;

public class Container {

	public List<User> userList;

	public List<User> getUserList() {

		return userList;
	}

	public void setUserList(List<User> userList) {

		this.userList = userList;
	}

	public Container() {

		userList = new ArrayList<User>();
	}

	public Container(List<User> userList) {

		this.userList = userList;
	}

}
