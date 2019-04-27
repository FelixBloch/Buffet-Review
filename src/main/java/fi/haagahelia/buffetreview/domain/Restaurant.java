package fi.haagahelia.buffetreview.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long restaurantId;
	
	// Restaurant Info
	@Column(name = "name", nullable = false, unique = true)
	private String restaurantName;
	@Column(name = "type", nullable = false)
	private String type;
	private String info;
	
	// Restaurant Address
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "postcode", nullable = false)
	private String postcode;
	@Column(name = "state", nullable = true)
	private String state;
	@Column(name = "country", nullable = false)
	private String country;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	@JsonIgnore
	private List<Review> reviews;
	
	public Restaurant(String name, String type, String info, String address, String city, String postcode, String state, String country) {
		this.restaurantName = name;
		this.type = type;
		this.info = info;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.state = state;
		this.country = country;
	}
	
	public Restaurant() {
		
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String name) {
		this.restaurantName = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
