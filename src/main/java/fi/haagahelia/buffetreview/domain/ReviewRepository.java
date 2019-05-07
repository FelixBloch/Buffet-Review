package fi.haagahelia.buffetreview.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import fi.haagahelia.buffetreview.domain.Review;

/**
 * Crud repository for the Review Entity.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {
	List<Review> findByTitle(String title);
}
