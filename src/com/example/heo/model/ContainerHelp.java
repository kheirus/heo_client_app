package com.example.heo.model;

import java.util.ArrayList;
import java.util.List;
import com.example.heo.model.Help;

public class ContainerHelp {

	public List<Help> helpList;

	public List<Help> getHelpList() {

		return helpList;
	}

	public void setHelpList(List<Help> helpList) {

		this.helpList = helpList;
	}

	public ContainerHelp() {

		helpList = new ArrayList<Help>();
	}

	public ContainerHelp(List<Help> helpList) {

		this.helpList = helpList;
	}

}
