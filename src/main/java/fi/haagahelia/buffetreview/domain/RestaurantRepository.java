package fi.haagahelia.buffetreview.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import fi.haagahelia.buffetreview.domain.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
		List<Restaurant> findByName(String name);
}

