package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.Constants;
import com.demo.entity.User;
import com.demo.service.UserService;

@RestController
@RequestMapping(Constants.USER_CONTROLLER_BASE_URL)
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping(Constants.LIST_USER_URL)
	public List<User> listUsers() {
		LOG.info("Retrieving list of all users...");
		return userService.listUsers();
	}

	@PostMapping(Constants.REGISTER_USER_URL)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		user.setIsActive(1);
		LOG.info("Registering new user...");
		LOG.info("User to be saved -> {}", user);
		User savedUser = userService.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(Constants.UPDATE_USER_URL)
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") int id, @RequestBody User user) {
		LOG.info("Updating user...");
		ResponseEntity<User> response = null;
		Optional<User> savedUser = userService.findUserById(id);
		if (savedUser.isPresent()) {
			LOG.info("User to be updated -> {}", savedUser);
			user.setId(id);
			User updatedUser = userService.updateUser(user);
			LOG.info("Updated user -> {}", user);
			response = new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(Constants.DELETE_USER_URL)
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") int id) {
		LOG.info("Deleting user...");
		ResponseEntity<User> response = null;
		Optional<User> savedUser = userService.findUserById(id);
		if (savedUser.isPresent()) {
			LOG.info("User to be deleted -> {}", savedUser);
			userService.deleteUser(savedUser.get());
			LOG.info("User is deleted.");
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
