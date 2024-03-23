package com.example.notiumb.security.config;

import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.security.filter.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/api-docs",
            "/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole(Rol.ADMIN.name())
                        .requestMatchers(GET, "/restaurante/**").hasAnyAuthority(Rol.ADMIN.name(), Rol.RESTAURANTE.name())
                        .requestMatchers(GET, "/ocionocturno/**").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(GET, "/cliente/**").hasAnyAuthority(Rol.ADMIN.name(),Rol.CLIENTE.name())
                        .requestMatchers(GET, "/rpp/**").hasAnyAuthority(Rol.RPP.name())
                        .anyRequest().authenticated()

                        .anyRequest().permitAll()
                        .anyRequest().hasRole(Rol.ADMIN.name())
                        .anyRequest().hasAnyAuthority(Rol.ADMIN.name())
                        .anyRequest().hasAnyAuthority(Rol.ADMIN.name(), Rol.RESTAURANTE.name(), Rol.OCIONOCTURNO.name(), Rol.CLIENTE.name(), Rol.RPP.name())
                        .anyRequest().permitAll()
                        .anyRequest().authenticated()


                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout
                                .logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }
}
