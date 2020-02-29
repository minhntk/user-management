package com.bkitsolution.usermanagement.controller;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

  private final HttpServletRequest request;

  @Autowired
  public AdminController(HttpServletRequest request) {
    this.request = request;
  }

  @GetMapping(value = "/customers")
  public String getManager(Model model) {
    configCommonAttributes(model);
    //model.addAttribute("books", bookRepository.readAll());
    return "manager";
  }

  private void configCommonAttributes(Model model) {
    model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
  }

  private KeycloakSecurityContext getKeycloakSecurityContext() {
    return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
  }

}
