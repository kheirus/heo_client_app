package com.example.heo.model;

import java.util.List;

import org.restlet.resource.ClientResource;



import android.util.Log;

public class CommentController {
	
	private ClientResource cr = new ClientResource(
			EngineConfiguration.gae_path+"/rest/coms");
	
	
	public CommentController() {
		EngineConfiguration.getInstance();
	}
	
	public void addComment(Comment comment){
		CommentControllerInterface cci = (CommentControllerInterface) cr.wrap(CommentControllerInterface.class);
		
		try{
			cci.addComment(comment);
		}catch(Exception e){
			Log.e("Error",e.toString());
		}
	}

	public List<Comment> getAllComment(){
		CommentControllerInterface cci = cr.wrap(CommentControllerInterface.class);
		
		ContainerComment content = cci.getComment();
		
		return content.getCommentList();
	}
}
