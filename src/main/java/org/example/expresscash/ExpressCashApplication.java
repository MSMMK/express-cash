package org.example.expresscash;

import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.constants.UserTypeEnum;
import org.example.expresscash.entity.RoleEnum;
import org.example.expresscash.model.RegisterRequest;
import org.example.expresscash.repositories.UserRepository;
import org.example.expresscash.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@Slf4j
public class ExpressCashApplication implements CommandLineRunner {
    @Autowired private AuthService authService;
    @Autowired private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExpressCashApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .username("admin")
                .email("admin@gmail.com")
                .govId(1l)
                .cityId(1l)
                .password("123456")
                .role(RoleEnum.ROLE_ADMIN)
                .userType(UserTypeEnum.ADMIN)
                .build();

        log.info("check if admin user registered");
        if (!userRepository.existsByUsername("admin")) {
            log.info("register admin user with usename {} and password {}", request.getUsername(), request.getPassword());
            authService.register(request);
        }
    }
}
