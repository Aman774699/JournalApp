package net.edigest.journalApp.configuration;


import net.edigest.journalApp.filters.JwtFilters;
import net.edigest.journalApp.service.UserDetailServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity
{
    @Autowired
    UserDetailServiceimpl userDetailServiceimpl;
    @Autowired
    JwtFilters jwtFilters;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf(cd->cd.disable()).authorizeHttpRequests(request->request
                 .requestMatchers("/journal/**","/user/**").authenticated().
                 requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS).maximumSessions(1));
              http.addFilterBefore(jwtFilters, UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception
    {
      auth.userDetailsService(userDetailServiceimpl).passwordEncoder((passwordEncoder()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)throws  Exception{
        return auth.getAuthenticationManager();
    }
}
