package ro.pub.cs.systems.pdsd.lab05.addressbook.model;

import android.net.Uri;

public class Contact {
	
	private Uri photo;
	private String name;
	private int starred;
	private int timesContacted;
	private long lastTimeContacted;
	
	public Contact() {
		photo = Uri.EMPTY;
		name = new String();
		starred = 0;
		timesContacted = 0;
		lastTimeContacted = 0;
	}
	
	public Contact(Uri photo, String name, int starred, int timesContacted, long lastTimeContacted) {
		this.photo = photo;
		this.name = name;
		this.starred = starred;
		this.timesContacted = timesContacted;
		this.lastTimeContacted = lastTimeContacted;
	}
	
	public void setPhoto(Uri photo) {
		this.photo = photo;
	}
	
	public Uri getPhoto() {
		return photo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setStarred(int starred) {
		this.starred = starred;
	}
	
	public int getStarred() {
		return starred;
	}
	
	public void setTimesContacted(int timesContacted) {
		this.timesContacted = timesContacted;
	}
	
	public int getTimesContacted() {
		return timesContacted;
	}
	
	public void setLastTimeContacted(long lastTimeContacted) {
		this.lastTimeContacted = lastTimeContacted;
	}
	
	public long getLastTimeContacted() {
		return lastTimeContacted;
	}

}
