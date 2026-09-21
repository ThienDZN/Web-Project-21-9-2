package vn.iotstar.example2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.example2.entity.AppUser;
import vn.iotstar.example2.entity.Role;
import vn.iotstar.example2.repository.AppUserRepository;
import vn.iotstar.example2.repository.RoleRepository;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seed(RoleRepository roles, AppUserRepository users, PasswordEncoder encoder, @Value("${app.demo.password:}") String demoPassword) { return args -> { Role role = roles.findByName("ROLE_USER").orElseGet(() -> roles.save(new Role("ROLE_USER"))); if (!demoPassword.isBlank() && !users.existsByUsernameIgnoreCase("demo-user")) { AppUser user = new AppUser("demo-user", "demo.example2@test.local", encoder.encode(demoPassword), "Example 2 Demo", role); user.setEnabled(true); users.save(user); } }; }
}
