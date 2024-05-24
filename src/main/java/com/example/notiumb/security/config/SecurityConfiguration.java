package com.example.notiumb.security.config;

import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.security.filter.JwtAuthenticationFilter;
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
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/doc/**",
            "/doc/swagger-ui/index.html#",
            "/auth/**",
            "/api-docs",
            "/api-docs/**",
            "/api/users/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/send-email/**",
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http


                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/v2/**", "/v3/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/doc/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/eventos/**").permitAll()
                        .requestMatchers("/cartasOcio/**").permitAll()
                        .requestMatchers("/listasOcio/**").permitAll()
                        .requestMatchers("/rpps/**").permitAll()
                        .requestMatchers("/ocioNocturno/**").permitAll()
                        .requestMatchers("/email/**").permitAll()
                        .requestMatchers("/email.html").permitAll()
                        .requestMatchers("/registrar/**").permitAll()
                        .requestMatchers("/verificar/**").permitAll()
                        .requestMatchers("/mesa/**").permitAll()
                        .requestMatchers("/turno/**").permitAll()
                        .requestMatchers("/restaurante/**").permitAll()
                        .requestMatchers("/comentario/**").permitAll()
                        .requestMatchers("/cliente/**").permitAll()
                        .requestMatchers("/producto/**").permitAll()
                        .requestMatchers("/productoFormato/**").permitAll()
                        .requestMatchers("/comprar/**").permitAll()
                        .requestMatchers("/promocion/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole(Rol.ADMIN.name())
                        .requestMatchers("/producto/**").hasAnyAuthority(Rol.RESTAURANTE.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(GET, "/restaurante/**").hasAnyAuthority(Rol.ADMIN.name(), Rol.RESTAURANTE.name())
                        .requestMatchers(GET, "/ocioNocturno/**").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(GET, "/cliente/**").hasAnyAuthority(Rol.ADMIN.name(),Rol.CLIENTE.name())
                        .requestMatchers(GET, "/rpps/**").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(GET, "/eventos/listarTodos").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name(), Rol.RPP.name())
                        .requestMatchers(GET, "/eventos/listarByOcio").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name(), Rol.RPP.name())
                        .requestMatchers(POST, "/eventos/crear").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(GET, "/cartasOcio/listar").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name(), Rol.RPP.name(), Rol.CLIENTE.name())
                        .requestMatchers(POST, "/cartasOcio/guardar").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(POST, "/eventos/fechas").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(POST, "/eventos/fechas/{id}").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
                        .requestMatchers(POST, "/eventos{id}").hasAnyAuthority(Rol.ADMIN.name(), Rol.OCIONOCTURNO.name())
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
