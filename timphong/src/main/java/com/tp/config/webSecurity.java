package com.tp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tp.service.impl.customUserDetailService;

@Configuration
@Order(1)
public class webSecurity {
    @Bean
    public UserDetailsService userDetailsService() {
        return new customUserDetailService();
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }     

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        http.authorizeRequests(request -> request
                .antMatchers("/", "/trangchu" ,"/timkiem", "/detail-phong/**")
                    .permitAll()
                .antMatchers("/admin/**", "/seller/**", "/profile/**", "/account/**", "/dathen", "/quantam")
                    .authenticated())
            .formLogin(login -> login
                .loginPage("/login")
                .usernameParameter("usernamelogin")
                .passwordParameter("passwordlogin")
                .defaultSuccessUrl("/trangchu"))
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/trangchu")
                .permitAll());
        http.csrf(csrf -> csrf.disable());

        http.authorizeRequests(requests -> requests
                .antMatchers("/admin/**")
                .hasAuthority("ADMIN"));
        
        http.authorizeRequests(request -> request
                .antMatchers("/seller/**")
                .hasAnyAuthority("ADMIN", "SELLER"));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/fonts/**", "/scss/**");
    }
}
