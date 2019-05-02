package fi.haagahelia.buffetreview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.buffetreview.domain.Restaurant;
import fi.haagahelia.buffetreview.domain.RestaurantRepository;
import fi.haagahelia.buffetreview.domain.Review;
import fi.haagahelia.buffetreview.domain.ReviewRepository;
import fi.haagahelia.buffetreview.domain.User;
import fi.haagahelia.buffetreview.domain.UserRepository;

@SpringBootApplication
public class BuffetReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuffetReviewApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ReviewRepository repository, RestaurantRepository rrepository, UserRepository urepository) {
		return (args) -> {
			User user1 = new User("user", "user@buffet-review.fi","$2a$10$lphEIgrl7r211MoxPmaKeOtBkmoCIFz7n9vO3lwfRUYBQMWR.Sd2i", "USER", "use", "using");
			User admin1 = new User("admin", "admin@buffet-review.fi","$2a$10$jD1Wm.VsfpLwpIDTt5zcEegnBoU6GNaVmAcx1tYx7nV5ciNvNCyFe", "ADMIN", "add", "adding");
			urepository.save(user1);
			urepository.save(admin1);
			
			rrepository.save(new Restaurant("Luckiefun's Restaurant", "asian, sushi, fusion", "This is a nice Asian buffet in the city centre.", "Fredrikinkatu 49", "Helsinki", "00101", "", "Finland"));
			rrepository.save(new Restaurant("Fuku Helsinki", "asian, sushi", "This is a nice sushi buffet in the city centre", "Mannerheimintie 18", "Helsinki", "00100", "Uusimaa", "Finland"));
			rrepository.save(new Restaurant("Food House", "asian, sushi, fusion, nepalese", "This is a asian lunch buffet located in Pasila", "Esterinportti 2", "Helsinki", "00240", "Uusimaa", "Finland"));
			
			//Date dateNow = new Date( );
			repository.save(new Review("Lunch at Luckiefun's", rrepository.findByRestaurantName("Luckiefun's Restaurant").get(0), urepository.findByUsername("admin").getUsername(), 4, "I went here for lunch with my colleagues and we had great sushi!", 2, "2019-04-11"));
			repository.save(new Review("Nice dinner at Fuku", rrepository.findByRestaurantName("Fuku Helsinki").get(0), "Tim Tester", 4, "The food was great and the servers excellent. The price was a bit high.", 4, "2019-03-10"));
			repository.save(new Review("This food was okay", rrepository.findByRestaurantName("Food House").get(0), "Jane Jameson", 3, "Nice lunch if you are in a hurry or on a budget. Nothing extraordinary but at least the staff was nice.", 1, "2019-04-07"));
			
		};
	}

}
