package net.javaguide.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.config.AppConstants;
import net.javaguide.springboot.entity.Role;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.AlreadyExistsException;
import net.javaguide.springboot.exception.RecordNotFoundException;
import net.javaguide.springboot.repository.RoleRepo;
import net.javaguide.springboot.repository.UserRepository;
import net.javaguide.springboot.service.IAuthenticationFacade;
import net.javaguide.springboot.service.UserService;

import org.modelmapper.ModelMapper;
//import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import java.util.List;
//import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	

	@Override
	public User createUser(User user) throws AlreadyExistsException{
		// TODO Auto-generated method stub
		
		Optional<User> u=userRepository.findByEmail(user.getEmail());
		if(u.isPresent()) {
			 throw new AlreadyExistsException("Email already exists, please use another email");
		}
		
	return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById() throws RecordNotFoundException {
		Authentication auth = authenticationFacade.getAuthentication();
		User dbUser = (User) auth.getPrincipal();
		Optional<User> optionalUser = userRepository.findById(dbUser.getId());
        if(optionalUser.isPresent()) {
        	return optionalUser;
        }else {
        	throw new RecordNotFoundException("User not found with id "+dbUser.getId());
        }
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public User updateUser(User user) {
		User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
	}

	@Override
	public void deleteUser() {
		Authentication auth = authenticationFacade.getAuthentication();
		User dbUser = (User) auth.getPrincipal();
		 userRepository.deleteById(dbUser.getId());

	}
	  @Override
	    public List<User> searchUsers(String query) {
	        List<User> products = userRepository.searchUsers(query);
	        return products;
	    }

	@Override
	public User findUserByEmail(String email) throws RecordNotFoundException {
		// TODO Auto-generated method stub
	User user=	userRepository.findByEmail(email).get();
		return user;
	}

	@Override
	public Optional<User> authenticateUser(String email, String Password) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
		System.err.println(email+""+passwordEncoder.encode(Password));
		Optional<User> user=userRepository.findByEmailAndPassword(email,Password);

		
		System.err.println("user::"+user);
		if(user.isPresent()) {
			return user;
		}else {
			throw new RecordNotFoundException();
		}
	}

//	@Override
//	public User registerUser(User user) throws AlreadyExistsException {
//		// TODO Auto-generated method stub
//		
//		
//		return userRepository.save(user);
//	}
	
	@Override
	public User registerUser(User userDto) {

		
		User user = modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepository.save(user);

		return newUser;
		
		
//		User user = modelMapper.map(userDto, User.class);
//		System.out.println("-------");
//System.out.println(user);
		// encoded the password
//		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));

//		Role r=new Role();
//		r.setId(1);
//		r.setName(AppConstants.NORMAL_USER.toString());
//		user.setRoles();
		// roles
//	Optional<Role> role = Optional.ofNullable(this.roleRepo.findById(AppConstants.NORMAL_USER).get());

//	if(role.isPresent()) {
//		user.getRoles().add(role.get());
//	}
//		roleRepo.save()
	
//		User newUser =userRepository.save(userDto);
//
//		return newUser;
	}

//	@Autowired
//	private UserRepository userRepository;

//	@Override
//	public User createUser(User user) throws AlreadyExistsException {
//		Optional<User> u = userRepository.findByEmail(user.getEmail());
//		if (u.isPresent()) {
//			throw new AlreadyExistsException("Email already Exists, Please use another email");
//		}
//		return userRepository.save(user);
//	}
//
//	@Override
//	public User getUserById(Long userId) throws RecordNotFoundException {
//		Optional<User> optionalUser = userRepository.findById(userId);
//		if (optionalUser.isPresent()) {
//			return optionalUser.get();
//		} else {
//			throw new RecordNotFoundException("User not Found with id " + userId);
//		}
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		return userRepository.findAll();
//	}
//
//	@Override
//	public User updateUser(User user) {
//		User existingUser = userRepository.findById(user.getId()).get();
//		existingUser.setFirstName(user.getFirstName());
//		existingUser.setLastName(user.getLastName());
//		existingUser.setEmail(user.getEmail());
//		existingUser.setPassword(user.getPassword());
//		User updatedUser = userRepository.save(existingUser);
//		return updatedUser;
//	}
//
//	@Override
//	public void deleteUser(Long userId) {
//		userRepository.deleteById(userId);
//	}
//
//	@Override
//	public void deleteAll() {
//		userRepository.deleteAll();
//	}
//
//	public UserRepository getUserRepository() {
//		return userRepository;
//	}
//
//	public void setUserRepository(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public User loginUser(User user) {
//		Optional<User> user1 = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
//		return user1.orElseGet(null);
//	}
//
//	@Override
//	public List<User> searchUsers(String query) {
//		return userRepository.searchUsers(query);
//	}
//
//	@Override
//	public Optional<User> findByEmail(String email) throws AlreadyExistsException {
//		return userRepository.findByEmail(email);
//	}

}
