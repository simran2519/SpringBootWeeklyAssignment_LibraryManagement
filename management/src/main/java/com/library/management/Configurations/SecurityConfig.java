package com.library.management.Configurations;

import com.library.management.Security.CustomLibraryMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    CustomLibraryMember customLibraryMember;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                auth->
                {
                    auth.requestMatchers(HttpMethod.POST, "/member/**").permitAll();
                        auth.requestMatchers("/member/**").permitAll();
                        auth.requestMatchers("/book/hmm").hasRole("USER");
                        auth.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//                .httpBasic(Customizer.withDefaults())
                .build();

//        httpSecurity.sessionManagement(
//                session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//        httpSecurity.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        //We can enable it by uncomment it and commenting formLogin and vice versa
//        httpSecurity.httpBasic(Customizer.withDefaults());
        //Here also we can disable csrf like this:-----
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        return httpSecurity.build();
    }
//
//    @Bean
//    UserDetailsService userDetailsService()
//    {
//        UserDetails user1= User.builder()
//                .username("hammu")
//                .password("$2a$12$jBDmZLa61gCEGYy0BW3afOL3DQB9LSE4wjmhg92Rdf4ttlecmDomq")
//                .roles("ADMIN")
//                .build();
//        UserDetails user2= User.builder()
//                .username("mom")
//                .password("$2a$12$jBDmZLa61gCEGYy0BW3afOL3DQB9LSE4wjmhg92Rdf4ttlecmDomq")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
    @Bean
    public UserDetailsService userDetailsService() {
            return customLibraryMember;
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customLibraryMember);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }
}






