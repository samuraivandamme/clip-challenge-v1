package com.example.clip.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.clip.entities.User;
import com.example.clip.repository.UserRepository;
/**
 * @author Ivan
 *
 */
@Configuration
public class LoadDatabase {
	
	private final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		
		User user1 = new User("ivan","munoz");
		User user2 = new User("veronica","garcia");
		User user3 = new User("ivan","vandamme");
		
		return args -> {
			log.info("Preloading " + repository.save(user1));
			log.info("Preloading " + repository.save(user2));
			log.info("Preloading " + repository.save(user3));
		};
		
	}

}
