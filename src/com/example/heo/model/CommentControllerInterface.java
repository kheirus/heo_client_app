package com.example.heo.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.example.heo.model.Comment;

public interface CommentControllerInterface {

	@Put
	public void addComment(Comment comment);
	@Get
	public ContainerComment getComment();
}
