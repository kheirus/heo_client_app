package com.example.heo.model;

import java.io.Serializable;



public class Help implements Serializable{
	
	private static final long serialVersionUID = -4565824613138366017L;
		private String titre;
		private String description;
		private String heure;
		
		public Help(){
			
		}

		

		public String getTitre() {
			return titre;
		}

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}



		public String getHeure() {
			return heure;
		}

		public void setHeure(String heure) {
			this.heure = heure;
		}
		
		
}
