package ru.kata.spring.boot_security.demo;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.configs.SuccessUserHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationTest {

    private UserDetailsService userDetailsService;

    public AuthenticationTest() {
    }

    @Test
    public void testUserRoleAuthentication() throws Exception {
        SuccessUserHandler successUserHandler = new SuccessUserHandler();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Pass userDetailsService as a parameter
        Authentication authentication = createAuthentication(userDetailsService, "user@example.com", "password", "ROLE_USER");

        successUserHandler.onAuthenticationSuccess(request, response, authentication);

        Mockito.verify(response).sendRedirect("/user/");
    }

    // Update the createAuthentication method signature to accept userDetailsService as a parameter
    private Authentication createAuthentication(UserDetailsService userDetailsService, String email, String password, String role) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        return authToken;
    }
}
