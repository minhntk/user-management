package com.bkitsolution.usermanagement.controller;

import com.bkitsolution.usermanagement.dto.request.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

//  @Autowired
//  AuthenticationManager authenticationManager;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<UserDetails> doLogin(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
    try {
//      Authentication authentication = authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//          loginRequestDTO.getUsername(),
//          loginRequestDTO.getPassword()
//        )
//      );
//
//      SecurityContextHolder.getContext().setAuthentication(authentication);
//      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//      return ResponseEntity
//        .status(HttpStatus.OK)
//        .body(userDetails);
      return null;
    } catch (Exception err) {
      System.out.println(err);
      return null;
      //return new ResponseEntity<String>().status(HttpStatus.FORBIDDEN);
    }
  }


  @RequestMapping(value = "/sso/login", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<UserDetails> ssoLogin(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
    try {
//      Authentication authentication = authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//          loginRequestDTO.getUsername(),
//          loginRequestDTO.getPassword()
//        )
//      );
//
//      SecurityContextHolder.getContext().setAuthentication(authentication);
//      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//      return ResponseEntity
//        .status(HttpStatus.OK)
//        .body(userDetails);
      return null;
    } catch (Exception err) {
      System.out.println(err);
      return null;
      //return new ResponseEntity<String>().status(HttpStatus.FORBIDDEN);
    }
  }


}
