package ru.kata.spring.boot_security.demo.configs;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/user").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .antMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
         .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .accessDeniedHandler(new AccessDeniedHandlerImpl());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService((UserDetailsService) userService);
        return daoAuthenticationProvider;
    }
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

}