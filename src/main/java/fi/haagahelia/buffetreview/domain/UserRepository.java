package fi.haagahelia.buffetreview.domain;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.buffetreview.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}
