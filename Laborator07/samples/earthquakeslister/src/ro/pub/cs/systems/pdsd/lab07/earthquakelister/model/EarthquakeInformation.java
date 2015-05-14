package ro.pub.cs.systems.pdsd.lab07.earthquakelister.model;

public class EarthquakeInformation {
	
	private double latitude, longitude;
	private double magnitude;
	private double depth;
	private String source;
	private String datetime;
	
	public EarthquakeInformation() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.magnitude = 0.0;
		this.depth = 0.0;
		this.source = new String();
		this.datetime = new String();
	}
	
	public EarthquakeInformation(
			double latitude,
			double longitude,
			double magnitude,
			double depth,
			String source,
			String datetime) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.magnitude = magnitude;
		this.depth = depth;
		this.source = source;
		this.datetime = datetime;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	public double getDepth() {
		return depth;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getDatetime() {
		return datetime;
	}

}
