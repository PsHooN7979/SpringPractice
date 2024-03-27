package org.example.userspring;

import jakarta.persistence.EntityManager;
import org.example.userspring.repository.JpaUserRepository;
import org.example.userspring.repository.UserRepository;
import org.example.userspring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {

    private final EntityManager entityManager;

    public Configure(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(entityManager);
    }
}
