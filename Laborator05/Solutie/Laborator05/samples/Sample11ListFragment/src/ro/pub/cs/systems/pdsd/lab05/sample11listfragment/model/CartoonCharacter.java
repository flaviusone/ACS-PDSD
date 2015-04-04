package ro.pub.cs.systems.pdsd.lab05.sample11listfragment.model;

import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.general.Constants;

public class CartoonCharacter {
	private String name;
	private String species;
	private String gender;
	private String debut;
	private String picture;
	
	public CartoonCharacter() {
		this.name = new String();
		this.species = new String();
		this.gender = new String();
		this.debut = new String();
		this.picture = new String();
	}
	
	public CartoonCharacter(String name, String species, String gender, String debut, String picture) {
		this.name = name;
		this.species = species;
		this.gender = gender;
		this.debut = debut;
		this.picture = picture;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpecies() {
		return species;
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender; 
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
		else if (attribute.equals(Constants.SPECIES_TAG))
			return species;
		else if (attribute.equals(Constants.GENDER_TAG))
			return gender;
		else if (attribute.equals(Constants.DEBUT_TAG))
			return debut;	
		else if (attribute.equals(Constants.PICTURE_TAG))
			return picture;		
		return null;		
	}
	
	public void set(String attribute, String value) {
		if (attribute.equals(Constants.NAME_TAG))
			setName(value);
		else if (attribute.equals(Constants.SPECIES_TAG))
			setSpecies(value);
		else if (attribute.equals(Constants.GENDER_TAG))
			setGender(value);
		else if (attribute.equals(Constants.DEBUT_TAG))
			setDebut(value);
		else if (attribute.equals(Constants.PICTURE_TAG))
			setPicture(value);		
	}
	
	public String toString() {
		return "Name: "+getName()+"Species: "+getSpecies()+"Gender: "+getGender()+"Debut: "+getDebut()+"Picture: "+getPicture();
	}

}
