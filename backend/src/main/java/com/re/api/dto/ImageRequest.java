package com.re.api.dto;

public class ImageRequest {

	    private String title;
	    private String type;
	    private int price;
	    private String location;
	    private int bedrooms;
	    private int bathrooms;
	    private int area;
	    private String imageUrl;
	    private String description;
	    private boolean available;

	    private String userName; // 🔥 from frontend

	    public ImageRequest() {
			// TODO Auto-generated constructor stub
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public int getBedrooms() {
			return bedrooms;
		}

		public void setBedrooms(int bedrooms) {
			this.bedrooms = bedrooms;
		}

		public int getBathrooms() {
			return bathrooms;
		}

		public void setBathrooms(int bathrooms) {
			this.bathrooms = bathrooms;
		}

		public int getArea() {
			return area;
		}

		public void setArea(int area) {
			this.area = area;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		@Override
		public String toString() {
			return "ImageRequest [title=" + title + ", type=" + type + ", price=" + price + ", location=" + location
					+ ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms + ", area=" + area + ", imageUrl="
					+ imageUrl + ", description=" + description + ", available=" + available + ", userName=" + userName
					+ "]";
		}
	    


	
	
	
	
}
