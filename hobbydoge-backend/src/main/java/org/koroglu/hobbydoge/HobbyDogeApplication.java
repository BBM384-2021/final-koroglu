package org.koroglu.hobbydoge;

import org.koroglu.hobbydoge.model.Role;
import org.koroglu.hobbydoge.model.RoleEnum;
import org.koroglu.hobbydoge.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HobbyDogeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HobbyDogeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			RoleRepository roleRepository) {
		return args -> {

			Role user = new Role(1, RoleEnum.USER);
			Role admin = new Role(2, RoleEnum.ADMIN);
			Role subClubAdmin = new Role(3, RoleEnum.SUBCLUB_ADMIN);
			roleRepository.save(user);
			roleRepository.save(admin);
			roleRepository.save(subClubAdmin);


		};
	}
}
