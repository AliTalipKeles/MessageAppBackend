package com.keles.discord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DiscordApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordApplication.class, args);
	}

}
