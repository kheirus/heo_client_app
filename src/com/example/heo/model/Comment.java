package com.example.heo.model;




import java.io.Serializable;




public class Comment implements Serializable {
	
	private static final long serialVersionUID = 5767146964746965938L;

		private String coms;
		private String heure;
		private String parent;
		
		public Comment(){
			
		}

		
		public String getComs() {
			return coms;
		}

		public void setComs(String coms) {
			this.coms = coms;
		}

		public String getHeure() {
			return heure;
		}



		public void setHeure(String heure) {
			this.heure = heure;
		}
		
		public String getParent() {
			return parent;
		}


		public void setParent(String parent) {
			this.parent = parent;
		}

		
}

