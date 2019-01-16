package io.ibex.bookservice.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class JwtSecurityConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/v1/**").hasAuthority("ROLE_BOOK_SERVICE")
                .antMatchers("**/swagger-ui.html").permitAll()
                .antMatchers("**/health").permitAll()
                .and()
                .csrf().disable();
    }
}
