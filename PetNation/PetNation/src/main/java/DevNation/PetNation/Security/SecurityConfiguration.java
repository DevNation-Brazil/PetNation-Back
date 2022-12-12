package DevNation.PetNation.Security;

import DevNation.PetNation.Security.Repositories.UserRepository;
import DevNation.PetNation.Security.Services.AuthenticationService;
import DevNation.PetNation.Security.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @EnableWebSecurity
    @Configuration
    @Order(2)
    public static class StandardSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationService authenticationService;

        @Autowired
        private TokenService tokenService;

        @Autowired
        private UserRepository userRepository;

        @Override
        @Bean
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }

        //Configurações de autenticação
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
        }

        //Configurações de autorização
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .anyRequest().authenticated().and().cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().addFilterBefore(new AuthByTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
        }

        @Override
        public void configure(WebSecurity webSecurity){
            webSecurity.ignoring().antMatchers("/content/image/**");
        }
    }

    @Configuration
    @EnableWebSecurity
    @Order(1)
    public static class APISecurityConfig extends WebSecurityConfigurerAdapter {

        @Value("${api.key.header-name}")
        private String principalRequestHeader;

        @Value("${api.key.value}")
        private String principalRequestValue;

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {

            AuthByApiKeyFilter filter = new AuthByApiKeyFilter(principalRequestHeader);
            filter.setAuthenticationManager(authentication -> {
                String principal = (String) authentication.getPrincipal();
                if (!principalRequestValue.equals(principal))
                {
                    throw new BadCredentialsException("API KEY INVÁLIDA");
                }
                authentication.setAuthenticated(true);
                return authentication;
            });
            httpSecurity.
                    antMatcher("/api/v1/key/**").
                    cors().and().
                    csrf().disable().
                    sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                    and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
        }

    }
}


