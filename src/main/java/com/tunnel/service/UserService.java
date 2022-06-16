package com.tunnel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tunnel.entity.User;
import com.tunnel.exceptions.UserNameAlreadyExistsException;

@SuppressWarnings("unused")
@Repository
@Transactional
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger("SampleLogger");
	
	public User createUser(User user) throws UserNameAlreadyExistsException {
		//Check if "username" is already existing and throw error if it doesn't
		List<User> users = userRepository.findByUserName(user.getUsername());
		if (users != null && users.size() > 0) {
			throw new UserNameAlreadyExistsException("Username "+ user.getUsername() + " already exist");
		}

		return userRepository.save(user);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> updateUser(User userUpdate) {
		Optional<User> user = userRepository.findById(userUpdate.getId());
		if (user == null) {
			return user;
		}

		userRepository.save(userUpdate);
		return user;
	}
}
