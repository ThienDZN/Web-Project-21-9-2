package vn.iotstar.example2.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.iotstar.example2.mapper.UserMapper;
import vn.iotstar.example2.repository.AppUserRepository;

@Service
public class Example2UserDetailsService implements UserDetailsService {
    private final AppUserRepository users; private final UserMapper mapper;
    public Example2UserDetailsService(AppUserRepository users, UserMapper mapper) { this.users = users; this.mapper = mapper; }
    @Override @Transactional(readOnly = true) public UserDetails loadUserByUsername(String login) { return users.findByUsernameIgnoreCaseOrEmailIgnoreCase(login, login).map(user -> new Example2Principal(user, mapper.toHeaderView(user))).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials")); }
}
