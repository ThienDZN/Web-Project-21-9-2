package vn.iotstar.example2.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.iotstar.example2.security.Example2UserDetailsService;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Bean DaoAuthenticationProvider authenticationProvider(Example2UserDetailsService users, PasswordEncoder encoder) { DaoAuthenticationProvider provider = new DaoAuthenticationProvider(users); provider.setPasswordEncoder(encoder); return provider; }
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider provider) throws Exception { return http.authenticationProvider(provider).authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/css/**", "/error").permitAll().anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("login").passwordParameter("password").defaultSuccessUrl("/", true).permitAll()).logout(logout -> logout.logoutSuccessUrl("/login?logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")).build(); }
}
