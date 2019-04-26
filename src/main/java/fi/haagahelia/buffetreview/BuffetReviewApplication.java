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
			User user1 = new User("user", "user@buffet-review.fi","$2a$10$lphEIgrl7r211MoxPmaKeOtBkmoCIFz7n9vO3lwfRUYBQMWR.Sd2i", "USER", "use", "using", "Ratapihantie 13", "Helsinki", "00520", "Uusimaa", "Finland");
			User admin1 = new User("admin", "admin@buffet-review.fi","$2a$10$jD1Wm.VsfpLwpIDTt5zcEegnBoU6GNaVmAcx1tYx7nV5ciNvNCyFe", "ADMIN", "add", "adding", "Ratapihantie 13", "Helsinki", "00520", "", "Finland");
			urepository.save(user1);
			urepository.save(admin1);
			
			rrepository.save(new Restaurant("Kung Fu Ravintola", "Asian, Fusion", "This is a nice Asian restaurant in Pasila", "Ruokatie 1", "Helsinki", 00520, "", "Finland"));
			rrepository.save(new Restaurant("Taj Mahal", "Indian", "This is a nice Indian restaurant in Pasila", "Ruokatie 2", "Helsinki", 00520, "Uusimaa", "Finland"));
			rrepository.save(new Restaurant("Wiesn", "German, Bavarian", "This is a Bavarian style outdoor restaurant located in Pasila", "Ruokatie 3", "Helsinki", 00520, "Uusimaa", "Finland"));
			
			//Date dateNow = new Date( );
			repository.save(new Review("Lunch at the Wiesn", rrepository.findByName("Wiesn").get(0), urepository.findByUsername("admin"), 4, "I went here for lunch with my colleagues and we had great beer and bratwurst.", 3, "11/04/2019"));
			repository.save(new Review("Nice dinner at Kung Fu", rrepository.findByName("Kung Fu Ravintola").get(0), urepository.findByUsername("admin"), 3, "The food was mediocre and the servers were rude. At least it was cheap.", 1, "10/03/2019"));
			repository.save(new Review("This food was on fire", rrepository.findByName("Taj Mahal").get(0), urepository.findByUsername("admin"), 5, "Great indian food but I am not used to the spice so the next morning I wasn't able to sit anymore.", 4, "07/04/2019"));
			
		};
	}

}
