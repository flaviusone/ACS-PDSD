package ro.pub.cs.systems.pdsd.lab05.addressbook.entities;

import android.os.Parcelable;

public abstract class Entity implements Parcelable {
	private String value;
	private int type;
	
	public Entity() {
		value = new String();
		type = -1;
	}
	
	public Entity(String value, int type) {
		this.value = value;
		this.type = type;
	}	
	
	public Entity(String value, String type) {
		this.value = value;
		this.type = convertTypeToIndex(type);
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setType(String type) {
		this.type = convertTypeToIndex(type);
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public abstract int convertTypeToIndex(String type);
	
}
