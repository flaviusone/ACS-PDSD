package ro.pub.cs.systems.pdsd.lab07.xkcdcartoondisplayer.entities;

import android.graphics.Bitmap;

public class XkcdCartoonInfo {
	
	private String cartoonTitle;
	private Bitmap cartoonContent;
	private String cartoonUrl;
	private String previousCartoonUrl, nextCartoonUrl;
	
	public XkcdCartoonInfo() {
		this.cartoonTitle = new String();
		this.cartoonContent = null;
		this.cartoonUrl = new String();
		this.previousCartoonUrl = new String();
		this.nextCartoonUrl = new String();
	}
	
	public XkcdCartoonInfo(String cartoonTitle,
			Bitmap cartoonContent,
			String cartoonUrl,
			String previousCartoonUrl,
			String nextCartoonUrl) {
		this.cartoonTitle = cartoonTitle;
		this.cartoonContent = cartoonContent;
		this.cartoonUrl = cartoonUrl;
		this.previousCartoonUrl = previousCartoonUrl;
		this.nextCartoonUrl = nextCartoonUrl;
	}
	
	public void setCartoonTitle(String cartoonTitle) {
		this.cartoonTitle = cartoonTitle;
	}
	
	public String getCartoonTitle() {
		return cartoonTitle;
	}
	
	public void setCartoonContent(Bitmap cartoonContent) {
		this.cartoonContent = cartoonContent;
	}
	
	public Bitmap getCartoonContent() {
		return cartoonContent;
	}
	
	public void setCartoonUrl(String cartoonUrl) {
		this.cartoonUrl = cartoonUrl;
	}
	
	public String getCartoonUrl() {
		return cartoonUrl;
	}
	
	public void setPreviousCartoonUrl(String previousCartoonUrl) {
		this.previousCartoonUrl = previousCartoonUrl;
	}
	
	public String getPreviousCartoonUrl() {
		return previousCartoonUrl;
	}
	
	public void setNextCartoonUrl(String nextCartoonUrl) {
		this.nextCartoonUrl = nextCartoonUrl;
	}
	
	public String getNextCartoonUrl() {
		return nextCartoonUrl;
	}

}
