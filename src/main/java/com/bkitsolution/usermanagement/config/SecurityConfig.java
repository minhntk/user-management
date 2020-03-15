package com.bkitsolution.usermanagement.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
  /**
   * Registers the KeycloakAuthenticationProvider with the authentication manager.
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) {
    SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
    grantedAuthorityMapper.setPrefix("ROLE_");

    KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
    keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
    auth.authenticationProvider(keycloakAuthenticationProvider);
  }

  /**
   * Defines the session authentication strategy.
   */
  @Bean
  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }

//  @Bean
//  public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
//    KeycloakAuthenticationProcessingFilter filter) {
//    FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//    registrationBean.setEnabled(false);
//    return registrationBean;
//  }
//
//  @Bean
//  public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
//    KeycloakPreAuthActionsFilter filter) {
//    FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//    registrationBean.setEnabled(false);
//    return registrationBean;
//  }
//
//  @Bean
//  public FilterRegistrationBean keycloakAuthenticatedActionsFilterBean(
//    KeycloakAuthenticatedActionsFilter filter) {
//    FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//    registrationBean.setEnabled(false);
//    return registrationBean;
//  }
//
//  @Bean
//  public FilterRegistrationBean keycloakSecurityContextRequestFilterBean(
//    KeycloakSecurityContextRequestFilter filter) {
//    FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//    registrationBean.setEnabled(false);
//    return registrationBean;
//  }

  @Bean
  @Override
  @ConditionalOnMissingBean(HttpSessionManager.class)
  protected HttpSessionManager httpSessionManager() {
    return new HttpSessionManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http
      .authorizeRequests()
      .antMatchers("/customers").hasAnyRole("Member", "USER")
      .antMatchers("/admin*").hasRole("ADMIN")
      .anyRequest().permitAll();
  }

//  @Bean
//  public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
//    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
//  }
}
