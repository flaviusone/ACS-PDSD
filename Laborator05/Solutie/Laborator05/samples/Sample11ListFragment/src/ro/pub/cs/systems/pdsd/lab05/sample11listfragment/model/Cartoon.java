package ro.pub.cs.systems.pdsd.lab05.sample11listfragment.model;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.general.Constants;

public class Cartoon {
	private String name;
	private String creator;
	private String debut;
	private String picture;
	private List<CartoonCharacter> characters;
	
	public Cartoon() {
		this.name = new String();
		this.creator = new String();
		this.debut = new String();
		this.picture = new String();
		this.characters = new ArrayList<CartoonCharacter>();
	}
	
	public Cartoon(String name, String creator, String debut, String picture, List<CartoonCharacter> characters) {
		this.name = name;
		this.creator = creator;
		this.debut = debut;
		this.picture = picture;
		this.characters = characters;
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
	
	public List<CartoonCharacter> getCharacters() {
		return characters;
	}
	
	public void setCharacters(List<CartoonCharacter> characters) {
		this.characters = characters;
	}
	
	public Object get(String attribute) {
		if (attribute.equals(Constants.NAME_TAG))
			return name;
		else if (attribute.equals(Constants.CREATOR_TAG))
			return creator;
		else if (attribute.equals(Constants.DEBUT_TAG))
			return debut;
		else if (attribute.equals(Constants.PICTURE_TAG))
			return picture;
		else if (attribute.equals(Constants.CHARACTERS_TAG))
			return characters;
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public void set(String attribute, Object value) {
		if (attribute.equals(Constants.NAME_TAG))
			setName((String)value);
		else if (attribute.equals(Constants.CREATOR_TAG))
			setCreator((String)value);
		else if (attribute.equals(Constants.DEBUT_TAG))
			setDebut((String)value);
		else if (attribute.equals(Constants.PICTURE_TAG))
			setPicture((String)value);
		else if (attribute.equals(Constants.CHARACTERS_TAG))
			setCharacters((List<CartoonCharacter>)value);
	}
	
	public String toString() {
		String result = "Name: "+getName()+"Creator: "+getCreator()+"Debut: "+getDebut()+"Picture: "+getPicture();
		for (CartoonCharacter character:characters)
			result += character.toString();
		return result;
	}
}
