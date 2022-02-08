package com.chm.interiorCM.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 정적파일 ignore
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/css/**", "/js/**","/img/**","/error/**","/lib/**");
    }

    // URL 정보 등록
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests( authorize -> authorize

                .mvcMatchers("/members/join", "/members/login").anonymous()
                .mvcMatchers("/articles/**",
                        "/",
                        "/members/modify/**"
                        ).permitAll()
                        .mvcMatchers(
                                "/boards/**"
                        )
                        .hasAnyRole("MEMBER", "ADMIN")
                .mvcMatchers("/adm/**").hasRole("ADMIN")
                .anyRequest()
                .denyAll()
        )
                .formLogin()
                    .loginPage("/members/login")
                    .loginProcessingUrl("/members/doLogin")
                    .usernameParameter("loginId")
                    .passwordParameter("loginPw")
                    .defaultSuccessUrl("/")

                .and()

                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("members/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)

                .and()
                    .sessionManagement()
                        .invalidSessionUrl("/")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
