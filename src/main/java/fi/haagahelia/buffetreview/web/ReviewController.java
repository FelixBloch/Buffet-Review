package fi.haagahelia.buffetreview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.buffetreview.domain.Restaurant;
import fi.haagahelia.buffetreview.domain.RestaurantRepository;
import fi.haagahelia.buffetreview.domain.Review;
import fi.haagahelia.buffetreview.domain.ReviewRepository;

/**
 * Main controller used in this application.
 */
@Controller
public class ReviewController {
	
	@Autowired
	private ReviewRepository repository;

	@Autowired
	private RestaurantRepository rrepository;
	
	/**
	 *  Mapping for the login page.
	 *  
	 *  The login page allows the user to login with a username or password.
	 *  Alternatively the user can go to the signup page.
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	/**
	 * First mapping for the reviews page.
	 * 
	 * Displays all reviews and allows to open each.
	 * Allows to create a new review.
	 * Enables the admin to edit and delete reviews.
	 */
	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public String reviews(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("reviews", repository.findAll());
		return "reviews";
	}
	
	/**
	 * Second mapping for the reviews page.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String reviewsAlt(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("reviews", repository.findAll());
		return "reviews";
	}
	
	/**
	 * Mapping for the restaurants page.
	 * 
	 * Displays all restaurants.
	 * Allows to create new restaurants.
	 * Enables admin to edit and delete restaurants.
	 */
	@RequestMapping(value = "/restaurants", method = RequestMethod.GET)
	public String restaurants(Model model) {
		model.addAttribute("restaurant", new Restaurant());
		model.addAttribute("restaurants", rrepository.findAll());
		return "restaurants";
	}
	
	/**
	 * Mapping for the showreview page for the individual reviews.
	 * 
	 * Shows individual review including restaurant location.
	 */
	@RequestMapping(value = "/showreview/{id}", method = RequestMethod.GET)
	public String showReview(@PathVariable("id") long id, Model model) {
		model.addAttribute("review", repository.findById(id).get());
		model.addAttribute("restaurants", rrepository.findAll());
		return "showreview";
	}
	
	/**
	 * Mapping for the addreview page.
	 * 
	 * Allows user to enter a review and optionally add a restaurant if not available.
	 */
	@RequestMapping(value = "/addreview")
	public String addReview(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("restaurants", rrepository.findAll());
		return "addreview";
	}
	
	/**
	 * Mapping for the addrestaurant page.
	 * 
	 * Allows user to create a new restaurant.
	 */
	@RequestMapping(value = "/addrestaurant")
	public String addRestaurant(Model model) {
		model.addAttribute("restaurant", new Restaurant());
		return "addrestaurant";
	}
	
	/**
	 * Mapping for saving newly created reviews.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Review review) {
		repository.save(review);
		return "redirect:reviews";
	}
	
	/**
	 * Mapping for saving newly created restaurants.
	 */
	@RequestMapping(value = "/saveRestaurant", method = RequestMethod.POST)
	public String save(Restaurant restaurant) {
		rrepository.save(restaurant);
		return "redirect:reviews";
	}
	
	/**
	 * mapping for deleting individual reviews.
	 */
	@RequestMapping(value = "/deleteReview/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReview(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		return "redirect:../reviews";
	}
	
	/**
	 * Mapping for deleting individual restaurants.
	 */
	@RequestMapping(value = "/deleteRestaurant/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteRestaurant(@PathVariable("id") long id, Model model) {
		rrepository.deleteById(id);
		return "redirect:../restaurants";
	}
	
	/**
	 * Mapping for editing individual reviews.
	 */
	@RequestMapping(value = "/editReview/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editReview(@PathVariable("id") long id, Model model) {
		model.addAttribute("review", repository.findById(id));
		model.addAttribute("restaurants", rrepository.findAll());
		return "editreview";
	}
	
	/**
	 * Mapping for editing individual restaurants.
	 */
	@RequestMapping(value = "/editRestaurant/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editRestaurant(@PathVariable("restaurantId") long id, Model model) {
		model.addAttribute("restaurant", rrepository.findById(id));
		return "editrestaurant";
	}

}
