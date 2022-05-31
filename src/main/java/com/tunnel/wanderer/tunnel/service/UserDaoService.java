package com.tunnel.wanderer.tunnel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tunnel.wanderer.tunnel.entity.User;

@Repository
@Transactional
public class UserDaoService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		userRepository.save(user);
		return user;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> updateUser(User userUpdate){
		Optional<User> user = userRepository.findById(userUpdate.getId());
		if(user == null) {
			return user;
		} 
		
		userRepository.save(userUpdate);
		return user;
	}
}
