package fi.haagahelia.buffetreview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fi.haagahelia.buffetreview.domain.RestaurantRepository;

@Controller
public class RestaurantController {
	@Autowired
	private RestaurantRepository repository;

}
