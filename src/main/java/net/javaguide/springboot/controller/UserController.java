package net.javaguide.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.AlreadyExistsException;
import net.javaguide.springboot.exception.ApiException;
import net.javaguide.springboot.model.JwtAuthRequest;
import net.javaguide.springboot.model.JwtAuthResponse;
import net.javaguide.springboot.model.ResponseHandler;
import net.javaguide.springboot.security.JwtTokenHelper;
import net.javaguide.springboot.service.UserService;
import net.javaguide.springboot.upload.FileDownloadUtil;
import net.javaguide.springboot.upload.FileUploadUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/api/v1/auth/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
//		System.out.println("loading username,,,");
//		
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, User.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	
	}

	private void authenticate(String username, String password) throws ApiException, Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		
		try {
			
		authenticationManager.authenticate(authenticationToken);
		

		} catch (BadCredentialsException e) {
			System.err.println(e);
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		user.setUsername(user.getEmail());
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String filecode = FileUploadUtil.saveFile(fileName, multipartFile, "Files-Upload");
		user.setPhoto(filecode);
		User registeredUser = userService.registerUser(user);
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
	}

	// build create User REST API
	@PostMapping("/signup")
	public ResponseEntity<Object> createUser(@ModelAttribute User user) {
		User savedUser = userService.createUser(user);
		return ResponseHandler.generateResponse("User Created Successfully", HttpStatus.CREATED, savedUser);
	}

	// build get user by id REST API
	// http://localhost:8080/api/users/1
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById() {
		User users = userService.getUserById().get();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Build Get All Users REST API
	// http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Build Update User REST API
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user) {
		user.setId(userId);
		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// Build Delete User REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser() {
		userService.deleteUser();
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}

	// Build Delete All Users API
//	@DeleteMapping("/deleteAll")
//	public ResponseEntity<String> deleteAll() {
//		userService.deleteAll();
//		return new ResponseEntity<>("All Users successfully deleted!", HttpStatus.OK);
//	}

	// Build Sign Up User API
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@ModelAttribute User user, @RequestParam("file") MultipartFile multipartFile)
			throws IOException, AlreadyExistsException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String filecode = FileUploadUtil.saveFile(fileName, multipartFile, "Files-Upload");
		user.setPhoto(filecode);
		User u = userService.createUser(user);
		return ResponseHandler.generateResponse("User Created Successfully", HttpStatus.CREATED, u);
	}

	// Build Login User API
//	@PostMapping("/login")
//	public ResponseEntity<Object> loginUser(@RequestBody User user) {
//		User i = userService.loginUser(user);
//		if (i == null) {
//			return ResponseHandler.generateResponse("Login Failed", HttpStatus.UNAUTHORIZED, null);
//		} else {
//			return ResponseHandler.generateResponse("Login Successfull", HttpStatus.OK, i);
//		}
//	}
	
	// Build Display File by Id REST API
		@GetMapping("products/displayFile/{fileCode}")
		public ResponseEntity<?> displayFile(@PathVariable("fileCode") String fileCode) {
			FileDownloadUtil downloadUtil = new FileDownloadUtil();

			Resource resource = null;
			try {
				resource = downloadUtil.getFileAsResource(fileCode);
			} catch (IOException e) {
				return ResponseEntity.internalServerError().build();
			}

			if (resource == null) {
				return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
			}
			String extension = "jpg";
			String fileName = resource.getFilename();
			int index = fileName.lastIndexOf('.');

			if (index > 0) {
				extension = fileName.substring(index + 1);
			}

			return ResponseEntity.ok()
					.contentType(MediaType
							.parseMediaType(extension == "jpg" ? MediaType.IMAGE_JPEG_VALUE : MediaType.IMAGE_PNG_VALUE))
					.body(resource);
		}

}
