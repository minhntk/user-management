package com.bkitsolution.usermanagement.controller;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

  private final HttpServletRequest request;

  @Autowired
  public LoginController(HttpServletRequest request) {
    this.request = request;
  }

  @GetMapping(value = "/")
  public String getHome(Principal principal, Model model) {
    Authentication authentication = (Authentication) principal;
    configCommonAttributes(model);
    return "index";
  }

  @GetMapping(value = "/customers")
  public String getCustomers(Model model) {
    configCommonAttributes(model);
    return "customers";
  }

  private void configCommonAttributes(Model model) {
    model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
  }

  private KeycloakSecurityContext getKeycloakSecurityContext() {
    return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
  }
}
