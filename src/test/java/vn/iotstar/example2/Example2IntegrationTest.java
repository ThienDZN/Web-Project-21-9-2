package vn.iotstar.example2;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.*;
import vn.iotstar.example2.entity.*;
import vn.iotstar.example2.repository.*;

@SpringBootTest @AutoConfigureMockMvc
class Example2IntegrationTest {
    @Autowired MockMvc mvc; @Autowired RoleRepository roles; @Autowired AppUserRepository users; @Autowired PasswordEncoder encoder;
    @BeforeEach void createUser() { if (users.existsByUsernameIgnoreCase("student2")) return; Role role = roles.findByName("ROLE_USER").orElseThrow(); AppUser user = new AppUser("student2", "student2@example.test", encoder.encode("valid-password"), "Student Two", role); user.setEnabled(true); users.save(user); }
    @Test void usernameLoginShowsFullNameInLayoutHeader() throws Exception { assertHeader("student2"); }
    @Test void emailLoginShowsFullNameInLayoutHeader() throws Exception { assertHeader("student2@example.test"); }
    private void assertHeader(String loginValue) throws Exception { MvcResult login = mvc.perform(post("/login").with(csrf()).param("login", loginValue).param("password", "valid-password")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/")).andReturn(); mvc.perform(get("/").session((MockHttpSession) login.getRequest().getSession(false))).andExpect(status().isOk()).andExpect(content().string(org.hamcrest.Matchers.containsString("Student Two"))).andExpect(content().string(org.hamcrest.Matchers.containsString("student2"))); }
}
