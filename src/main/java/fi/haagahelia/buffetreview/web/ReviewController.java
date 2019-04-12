package fi.haagahelia.buffetreview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.buffetreview.domain.Review;
import fi.haagahelia.buffetreview.domain.ReviewRepository;

@Controller
public class ReviewController {
	@Autowired
	private ReviewRepository repository;
	
	@RequestMapping(value="/reviews", method=RequestMethod.GET)
	public String reviews(Model model) {
	model.addAttribute("review", new Review());
	model.addAttribute("reviews", repository.findAll());
	return "reviews";
	}
	
	@RequestMapping(value = "/addreview")
	public String addReview(Model model){
		model.addAttribute("review", new Review());
		model.addAttribute("restaurants", repository.findAll());
		return "addreview";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Review review){
		repository.save(review);
		return "redirect:reviews";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReview(@PathVariable("id") long id, Model model) {
	repository.deleteById(id);
	return "redirect:../reviews";
	}
	
	@RequestMapping(value = "/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editReview(@PathVariable("id") long id, Model model){
	model.addAttribute("review", repository.findById(id));
	model.addAttribute("restaurants", repository.findAll());
	return "editreview";
	}
	
	@RequestMapping(value="/login")
    public String login() {
    	return "login";
    }

}
