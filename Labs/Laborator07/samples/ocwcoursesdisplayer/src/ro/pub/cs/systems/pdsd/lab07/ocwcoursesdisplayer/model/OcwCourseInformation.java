package ro.pub.cs.systems.pdsd.lab07.ocwcoursesdisplayer.model;

import android.graphics.Bitmap;

public class OcwCourseInformation {
	
	private Bitmap logo;
	private String name;
	
	public OcwCourseInformation() {
		this.logo = null;
		this.name = new String();
	}
	
	public OcwCourseInformation(Bitmap logo, String name) {
		this.logo = logo;
		this.name = name;
	}
	
	public void setLogo(Bitmap logo) {
		this.logo = logo;
	}
	
	public Bitmap getLogo() {
		return logo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
