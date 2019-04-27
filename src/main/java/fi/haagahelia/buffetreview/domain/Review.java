package fi.haagahelia.buffetreview.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId")
	@CreatedBy
	private User user;
	
	// Review data
	private String title;
	private int rating;
	private String reviewText;
	private int price;
	private String reviewDate;
	private String username;
	
	public Review(String title, Restaurant restaurant, String username, int rating, String reviewText, int price, String reviewDate) {
		this.title = title;
		this.restaurant = restaurant;
		this.username = username;
		this.rating = rating;
		this.reviewText = reviewText;
		this.price = price;
		this.reviewDate = reviewDate;
	}
	
	public Review() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
