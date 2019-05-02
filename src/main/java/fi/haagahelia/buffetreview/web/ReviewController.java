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

@Controller
public class ReviewController {
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private RestaurantRepository rrepository;
	
	@RequestMapping(value="/login")
    public String login() {
    	return "login";
    }
	
	@RequestMapping(value="/reviews", method=RequestMethod.GET)
	public String reviews(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("reviews", repository.findAll());
		return "reviews";
	}
	
	@RequestMapping(value="/restaurants", method=RequestMethod.GET)
	public String restaurants(Model model) {
	model.addAttribute("restaurant", new Restaurant());
	model.addAttribute("restaurants", rrepository.findAll());
	return "restaurants";
	}
	
	@RequestMapping(value = "/showreview/{id}", method=RequestMethod.GET)
	public String showReview(@PathVariable("id") long id, Model model){
		model.addAttribute("review", repository.findById(id).get());
		model.addAttribute("restaurants", rrepository.findAll());
		return "showreview";
	}
	
	@RequestMapping(value = "/addreview")
	public String addReview(Model model){
		model.addAttribute("review", new Review());
		model.addAttribute("restaurants", rrepository.findAll());
		return "addreview";
	}
	
	@RequestMapping(value = "/addrestaurant")
	public String addRestaurant(Model model) {
		model.addAttribute("restaurant", new Restaurant());
		return "addrestaurant";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Review review){
		repository.save(review);
		return "redirect:reviews";
	}
	
	@RequestMapping(value = "/saveRestaurant", method = RequestMethod.POST)
	public String save(Restaurant restaurant){
		rrepository.save(restaurant);
		return "redirect:reviews";
	}
	
	@RequestMapping(value = "/deleteReview/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReview(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		return "redirect:../reviews";
	}
	
	@RequestMapping(value = "/deleteRestaurant/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteRestaurant(@PathVariable("id") long id, Model model) {
		rrepository.deleteById(id);
		return "redirect:../restaurants";
	}
	
	@RequestMapping(value = "/editReview/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editReview(@PathVariable("id") long id, Model model){
		model.addAttribute("review", repository.findById(id));
		model.addAttribute("restaurants", rrepository.findAll());
		return "editreview";
	}
	
	@RequestMapping(value = "/editRestaurant/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editRestaurant(@PathVariable("restaurantId") long id, Model model){
		model.addAttribute("restaurant", rrepository.findById(id));
		return "editrestaurant";
	}
	

}
