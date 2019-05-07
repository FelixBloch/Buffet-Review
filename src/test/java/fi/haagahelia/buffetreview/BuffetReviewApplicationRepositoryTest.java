package fi.haagahelia.buffetreview;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.buffetreview.domain.Restaurant;
import fi.haagahelia.buffetreview.domain.RestaurantRepository;
import fi.haagahelia.buffetreview.domain.Review;
import fi.haagahelia.buffetreview.domain.ReviewRepository;
import fi.haagahelia.buffetreview.domain.User;
import fi.haagahelia.buffetreview.domain.UserRepository;
import fi.haagahelia.buffetreview.filehandling.FileModelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuffetReviewApplicationRepositoryTest {
	
	@Autowired
	private FileModelRepository frepository;
	
	@Autowired
	private RestaurantRepository resrepository;
	
	@Autowired
	private ReviewRepository revrepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findByTitleShouldReturnRestaurantName() {
		List<Review> reviews = revrepository.findByTitle("Lunch at Luckiefun's");
		assertThat(reviews).hasSize(1);
		assertThat(reviews.get(0).getRestaurant().getRestaurantName()).isEqualTo("Luckiefun's Restaurant");
	}
	
	@Test
	public void findByTitleShouldReturnUsername() {
		List<Review> reviews = revrepository.findByTitle("Lunch at Luckiefun's");
		assertThat(reviews.get(0).getUsername()).isEqualTo("admin");
	}
	
	@Test
	public void findByRestaurantNameShouldReturnType() {
		List<Restaurant> restaurants = resrepository.findByRestaurantName("Fuku Helsinki");
		assertThat(restaurants).hasSize(1);
		assertThat(restaurants.get(0).getType()).isEqualTo("asian, sushi");
	}
	
	@Test
	public void findByUsernameShouldReturnRole() {
		User users = urepository.findByUsername("admin");
		assertThat(users.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void findByEmailShouldReturnUsername() {
		User users = urepository.findByEmail("admin@buffet-review.fi");
		assertThat(users.getUsername()).isEqualTo("admin");
	}
	
	@Test
	public void createNewReview() {
		Review review = new Review("This is a test", resrepository.findByRestaurantName("Food House").get(0), "The Tester", 4, "Testing wasn't my choice it's my duty.", 4, "2019-05-02");
		revrepository.save(review);
		assertThat(review.getId()).isNotNull();
	}
	
	@Test
	public void createNewRestaurant() {
		Restaurant restaurant = new Restaurant("Test Restaurant", "test, success", "Do not fret, this is a test!", "Test Street 1", "Helsinki", "00100", "Uusimaa", "Finland");
		resrepository.save(restaurant);
		assertThat(restaurant.getRestaurantId()).isNotNull();
	}
	
	@Test
	public void createNewUser() {
		User user = new User("testuser", "testuser@test.com", "testuser", "TEST", "test", "user");
		urepository.save(user);
		assertThat(user.getUserId()).isNotNull();
	}
	
	@Test
	public void deleteReview() {
		List<Review> reviews = revrepository.findByTitle("This is a test");
		assertThat(reviews).hasSize(1);
		revrepository.deleteById(reviews.get(0).getId());
		List<Review> deletedReview = revrepository.findByTitle("This is a test");
		assertThat(deletedReview).hasSize(0);
	}
	
	@Test
	public void deleteRestaurant() {
		List<Restaurant> restaurants = resrepository.findByRestaurantName("Test Restaurant");
		assertThat(restaurants).hasSize(1);
		resrepository.deleteById(restaurants.get(0).getRestaurantId());
		List<Restaurant> deletedRestaurants = resrepository.findByRestaurantName("Test Restaurant");
		assertThat(deletedRestaurants).hasSize(0);
	}
	
	@Test
	public void deleteUser() {
		User user = urepository.findByUsername("testuser");
		assertThat(user).isNotNull();
		urepository.deleteById(user.getUserId());
		User deletedUser = urepository.findByUsername("testuser");
		assertThat(deletedUser).isNull();
	}
	

}
