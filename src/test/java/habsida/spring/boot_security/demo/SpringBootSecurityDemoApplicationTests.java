package habsida.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {

    @Autowired
    private SpringBootSecurityDemoApplication app;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
	void contextLoads() {
	}

    @Test
    public void applicationStarts() {
        app.main(new String[]{});
    }

    
    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    void adminRoleAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertThat(auth).isNotNull();
        assertThat(auth.getName()).isEqualTo("admin");
        assertThat(auth.getAuthorities()).extracting("authority").containsExactly("ROLE_USER", "ROLE_ADMIN");
    }

}

