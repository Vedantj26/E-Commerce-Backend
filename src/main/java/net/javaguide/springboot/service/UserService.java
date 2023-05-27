package net.javaguide.springboot.service;

import java.util.List;
import java.util.Optional;

import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.AlreadyExistsException;
import net.javaguide.springboot.exception.RecordNotFoundException;

public interface UserService {

//	User createUser(User user) throws AlreadyExistsException;
//
//	User getUserById(Long userId) throws AlreadyExistsException;
//
//	List<User> getAllUsers();
//
//	User updateUser(User user);
//
//	void deleteUser(Long userId);
//
//	void deleteAll();
//
//	User loginUser(User user);
//
//	List<User> searchUsers(String query);
//
//	Optional<User> findByEmail(String email) throws AlreadyExistsException;
	
	User createUser(User user) throws AlreadyExistsException;

	User registerUser(User user) ;

	Optional<User> getUserById() throws RecordNotFoundException;

	List<User> getAllUsers();

	User updateUser(User user);

	void deleteUser();

	List<User> searchUsers(String query);

	User findUserByEmail(String email) throws RecordNotFoundException;;

	Optional<User> authenticateUser(String email, String Password) throws RecordNotFoundException;
}
