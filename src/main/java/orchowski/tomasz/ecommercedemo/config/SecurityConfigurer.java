package orchowski.tomasz.ecommercedemo.config;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.ecommercedemo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true,proxyTargetClass = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //H2 in mem settings
        http.authorizeRequests(authorize -> {
            authorize
                    .antMatchers("/h2-console/**").permitAll() //do not use in production!
                    .antMatchers("/", "/login", "/resources/**").permitAll()
                    .antMatchers("/css/**", "/js/**", "/webjars/**","**/webjars/**").permitAll()
                    .antMatchers("/user/**").fullyAuthenticated();
        }).
                httpBasic().
                and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin().and();



        //Loggin
        http.formLogin(configurer ->{
            configurer
                    .loginProcessingUrl("/login")
                    .loginPage("/login").permitAll()
                    .successForwardUrl("/")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login/?error");
        } ).
        logout(configurer ->{
            configurer.
                    logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET")).
            logoutSuccessUrl("/").
            permitAll();
        } );

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.
        ignoring().
        antMatchers("/resources/**", "/static/**","/webjars/**");
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService AppUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User : " + s + " not found"));
            }
        };
    }



}
