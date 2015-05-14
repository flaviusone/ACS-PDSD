package ro.pub.cs.systems.pdsd.lab07.landmarklister.model;

public class LandmarkInformation {
	
	private double latitude, longitude;
	private String toponymName;
	private long population;
	private String fCodeName;
	private String name;
	private String wikipediaWebPageAddress;
	private String countryCode;
	
	public LandmarkInformation() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.toponymName = new String();
		this.population = 0;
		this.fCodeName = new String();
		this.name = new String();
		this.wikipediaWebPageAddress = new String();
		this.countryCode = new String();
	}
	
	public LandmarkInformation(
			double latitude,
			double longitude,
			String toponyName,
			long population,
			String fCodeName,
			String name,
			String wikipediaWebPageAddress,
			String countryCode) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.toponymName = toponyName;
		this.population = population;
		this.fCodeName = fCodeName;
		this.name = name;
		this.wikipediaWebPageAddress = wikipediaWebPageAddress;
		this.countryCode = countryCode;
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
	
	public void setToponymName(String toponyName) {
		this.toponymName = toponyName;
	}	
	
	public String getToponymName() {
		return toponymName;
	}
	
	public void setPopulation(long population) {
		this.population = population;
	}	
	
	public long getPopulation() {
		return population;
	}
	
	public void setFCodeName(String fCodeName) {
		this.fCodeName = fCodeName;
	}	
	
	public String getFCodeName() {
		return fCodeName;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}	
	
	public void setWikipediaWebPageAddress(String wikipediaWebPageAddress) {
		this.wikipediaWebPageAddress = wikipediaWebPageAddress;
	}
	
	public String getWikipediaWebPageAddress() {
		return wikipediaWebPageAddress;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

}
