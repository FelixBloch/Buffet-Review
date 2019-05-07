package fi.haagahelia.buffetreview;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.buffetreview.web.FileController;
import fi.haagahelia.buffetreview.web.ReviewController;
import fi.haagahelia.buffetreview.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuffetReviewApplicationTests {
	
	@Autowired
	private FileController fcontroller;
	
	@Autowired
	private ReviewController rcontroller;
	
	@Autowired
	private UserController ucontroller;
	
	@Test
	public void contextLoads() {
		assertThat(fcontroller).isNotNull();
		assertThat(rcontroller).isNotNull();
		assertThat(ucontroller).isNotNull();
	}

}
