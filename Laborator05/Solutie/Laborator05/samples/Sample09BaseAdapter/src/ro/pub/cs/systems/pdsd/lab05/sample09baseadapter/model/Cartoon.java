package ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.model;

import ro.pub.cs.systems.pdsd.lab05.sample09baseadapter.general.Constants;

public class Cartoon {
	private String name;
	private String creator;
	private String debut;
	private String picture;
	
	public Cartoon() {
		this.name = new String();
		this.creator = new String();
		this.debut = new String();
		this.picture = new String();
	}
	
	public Cartoon(String name, String creator, String debut, String picture) {
		this.name = name;
		this.creator = creator;
		this.debut = debut;
		this.picture = picture;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getDebut() {
		return debut;
	}
	
	public void setDebut(String debut) {
		this.debut = debut;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture; 
	}
	
	public String get(String attribute) {
		if (attribute.equals(Constants.NAME_TAG))
			return name;
		else if (attribute.equals(Constants.CREATOR_TAG))
			return creator;
		else if (attribute.equals(Constants.DEBUT_TAG))
			return debut;
		else if (attribute.equals(Constants.PICTURE_TAG))
			return picture;		
		return null;		
	}
	
	public void set(String attribute, String value) {
		if (attribute.equals(Constants.NAME_TAG))
			setName(value);
		else if (attribute.equals(Constants.CREATOR_TAG))
			setCreator(value);
		else if (attribute.equals(Constants.DEBUT_TAG))
			setDebut(value);
		else if (attribute.equals(Constants.PICTURE_TAG))
			setPicture(value);
	}
	
	public String toString() {
		return "Name: "+getName()+"Creator: "+getCreator()+"Debut: "+getDebut()+"Picture: "+getPicture();
	}

}
