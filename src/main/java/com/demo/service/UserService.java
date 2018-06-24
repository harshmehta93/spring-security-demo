package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entity.CustomUserDetails;
import com.demo.entity.User;
import com.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static final String USERNAME_NOT_FOUND_MESSAGE = "Username not found.";

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByName(username);

		optionalUser.orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE));

		return optionalUser.map(CustomUserDetails::new).get();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> listUsers() {
		return userRepository.findAll();
	}

	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
