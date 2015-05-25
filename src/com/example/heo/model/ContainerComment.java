package com.example.heo.model;

import java.util.ArrayList;
import java.util.List;


public class ContainerComment {

	public List<Comment> commentList;

	public List<Comment> getCommentList() {

		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {

		this.commentList = commentList;
	}

	public ContainerComment() {

		commentList = new ArrayList<Comment>();
	}

	public ContainerComment(List<Comment> commentList) {

		this.commentList = commentList;
	}

}
