package com.tunnel.wanderer.tunnel.service;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tunnel.wanderer.tunnel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}