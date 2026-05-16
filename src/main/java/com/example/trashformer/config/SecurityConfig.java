package com.example.trashformer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/error", "/css/**", "/uploads/**", "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/warga").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/petugas/**").hasRole("PETUGAS")
                        .requestMatchers("/warga/**").hasRole("WARGA")
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers(
                                "/api/users", "/api/users/**",
                                "/api/petugas", "/api/petugas/**",
                                "/api/tipe-sampah", "/api/tipe-sampah/**",
                                "/api/reports", "/api/reports/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/warga", "/api/warga/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/laporan-sampah/*/hasil").hasAnyRole("PETUGAS", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/laporan-sampah", "/api/laporan-sampah/**").hasAnyRole("PETUGAS", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/laporan-sampah").hasAnyRole("WARGA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/laporan-sampah", "/api/laporan-sampah/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/laporan-sampah", "/api/laporan-sampah/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/laporan-sampah", "/api/laporan-sampah/**").hasAnyRole("PETUGAS", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/pembayaran").hasAnyRole("WARGA", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/pembayaran", "/api/pembayaran/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/pembayaran", "/api/pembayaran/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/pembayaran", "/api/pembayaran/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/setoran").hasAnyRole("WARGA", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/setoran", "/api/setoran/**").hasAnyRole("PETUGAS", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/setoran", "/api/setoran/**").authenticated()
                        .requestMatchers("/api/dashboard/admin").hasRole("ADMIN")
                        .requestMatchers("/api/dashboard/petugas/**").hasAnyRole("PETUGAS", "ADMIN")
                        .requestMatchers("/api/dashboard/warga/**").hasAnyRole("WARGA", "ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
