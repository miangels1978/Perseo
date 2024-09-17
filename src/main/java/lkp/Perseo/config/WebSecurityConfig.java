package lkp.Perseo.config;


import lkp.Perseo.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test/admin").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/pets/newPets").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/pets/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/pets/update/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/pets/getAll").permitAll()
                                .requestMatchers("/api/v1/pets/availablePets").permitAll()
                                .requestMatchers("/api/v1/pets/adoptedPets").permitAll()
                                .requestMatchers("/api/v1/pets/petId/**").permitAll()
                                .requestMatchers("/api/v1/donations/createDonation").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/v1/donations/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/donations/deleteAll").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/donations/update/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/donations/getAll").permitAll()
                                .requestMatchers("/api/v1/donations/getDonation/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

