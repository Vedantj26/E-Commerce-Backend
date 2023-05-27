package net.javaguide.springboot.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.Transient;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.AlreadyExistsException;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE " + "u.email LIKE CONCAT('%', :query, '%')"
			+ "Or u.firstName LIKE CONCAT('%', :query, '%')")
	List<User> searchUsers(String query);

	@Transient
	Optional<User> findByLastName(String lastName);

	@Transient
	Optional<User> findByEmailAndPassword(String email, String password);

	@Transient
	Optional<User> findByEmailOrPassword(String email, String password);

	@Transient
	Optional<User> findByEmail(String email) throws AlreadyExistsException;
}
