package com.tunnel.service;

import com.tunnel.entity.AuthUserDetails;
import com.tunnel.entity.User;
import com.tunnel.entity.UserRole;
import com.tunnel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user = userService.findByUserName(username);
            //Load UserRole class
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserRole> query = cb.createQuery(UserRole.class);
            Root<UserRole> root = query.from(UserRole.class);
            query.select(root);
            root.fetch("role");
            Long id = user.get().getRoleId();
            query.where(cb.equal(root.get("userRoleId"), id));
            UserRole userRole = entityManager.createQuery(query).getSingleResult();
            UserDetails userDetails = new AuthUserDetails(user.get(), userRole.getRole());
            return userDetails;
    }
}
