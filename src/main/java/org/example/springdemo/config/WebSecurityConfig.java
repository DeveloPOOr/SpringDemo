package org.example.springdemo.config;

import org.example.springdemo.model.Permission;
import org.example.springdemo.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource postgresDataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(postgresDataSource);
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("password"))
//                        .authorities(Role.USER.getGrantedAuthority())
//                        .build(),
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities(Role.ADMIN.getGrantedAuthority())
//                        .build()
//        );


        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/people/**").hasAuthority(Permission.READ.getPermission())
                .antMatchers(HttpMethod.POST, "/people/**").hasAuthority(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.PATCH, "/people/**").hasAuthority(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/people/**").hasAuthority(Permission.WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");

    }



    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
