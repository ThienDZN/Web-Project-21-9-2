package vn.iotstar.example2.security;

import java.util.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.iotstar.example2.dto.UserHeaderView;
import vn.iotstar.example2.entity.AppUser;

public final class Example2Principal implements UserDetails {
    private final String username, password; private final boolean enabled; private final UserHeaderView profile; private final List<GrantedAuthority> authorities;
    public Example2Principal(AppUser user, UserHeaderView profile) { username = user.getUsername(); password = user.getPassword(); enabled = user.isEnabled(); this.profile = profile; authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName())); }
    public String getEmail() { return profile.email(); } public String getFullName() { return profile.fullName(); } public String getImageUrl() { return profile.imageUrl(); }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; } @Override public String getPassword() { return password; } @Override public String getUsername() { return username; } @Override public boolean isEnabled() { return enabled; }
}
