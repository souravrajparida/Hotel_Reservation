package com.example.reserve.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Room")
public class Room {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 
	    private String type;
	    private String conditioner;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getConditioner() {
			return conditioner;
		}
		public void setConditioner(String conditioner) {
			this.conditioner = conditioner;
		}
	    
	    
	    
		
	    

}
