package com.scncm.config;

import com.scncm.service.SimpleSocialUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;
    // todo set to private
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)/*.passwordEncoder(passwordEncoder())*/;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("favicon.ico", "/resources/**", "/j_spring_security_check",
                            "/register/**", "/socialRegister/facebook", "/socialRegister/facebook/**",
                            "/auth/**", "/dialog/**")
                        .permitAll()
                .and()
                    .authorizeRequests()
                        .anyRequest()
                            .hasAnyRole("ADMIN", "USER", "MODERATOR")
                /*.and()
                    .exceptionHandling()
                            .accessDeniedPage("/login?session_expired")*/
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/wall")
                        .failureUrl("/login?error")
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")
                        .loginProcessingUrl("/j_spring_security_check")
                            .permitAll()
                .and()
                    .apply(new SpringSocialConfigurer())
                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/j_spring_security_logout"))
                        .deleteCookies("remove")
                        .invalidateHttpSession(true)
                        .logoutUrl("/j_spring_security_logout")
                        .logoutSuccessUrl("/login?logout")
                            .permitAll()
                .and()
                    .csrf()
                        .disable()
                    /*.exceptionHandling()
                        .accessDeniedPage("/403")*/;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        logoutSuccessHandler.setTargetUrlParameter("redirect");
        logoutSuccessHandler.setDefaultTargetUrl("/login?logout");
        return logoutSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailsService(userDetailsService());
    }

    @Bean(name="myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
