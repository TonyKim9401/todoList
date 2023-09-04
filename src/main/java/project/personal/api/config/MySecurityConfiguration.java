package project.personal.api.config;


import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class MySecurityConfiguration {

    @Bean
    public WebSecurityCustomizer configure(HandlerMappingIntrospector introspector) {
        return (web) -> web.ignoring()
                .requestMatchers(new MvcRequestMatcher(introspector, "/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.securityMatcher(EndpointRequest.toAnyEndpoint())
                    .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests((requests) -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"))
                        .httpBasic(withDefaults())
                .build();
    }

}
