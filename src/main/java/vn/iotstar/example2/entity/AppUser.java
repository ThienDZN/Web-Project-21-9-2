package vn.iotstar.example2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_users", uniqueConstraints = { @UniqueConstraint(name = "uk_example2_username", columnNames = "username"), @UniqueConstraint(name = "uk_example2_email", columnNames = "email") })
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 50) private String username;
    @Column(nullable = false, length = 150) private String email;
    @Column(nullable = false) private String password;
    @Column(name = "full_name", nullable = false, length = 150) private String fullName;
    @Column(name = "image_url", length = 500) private String imageUrl;
    @Column(nullable = false) private boolean enabled;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "role_id") private Role role;
    protected AppUser() { }
    public AppUser(String username, String email, String password, String fullName, Role role) { this.username = username; this.email = email; this.password = password; this.fullName = fullName; this.role = role; }
    public Long getId() { return id; } public String getUsername() { return username; } public String getEmail() { return email; } public String getPassword() { return password; } public String getFullName() { return fullName; } public String getImageUrl() { return imageUrl; } public boolean isEnabled() { return enabled; } public Role getRole() { return role; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; } public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
