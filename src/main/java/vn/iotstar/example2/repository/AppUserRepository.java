package vn.iotstar.example2.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.example2.entity.AppUser;
public interface AppUserRepository extends JpaRepository<AppUser, Long> { Optional<AppUser> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email); boolean existsByUsernameIgnoreCase(String username); }
