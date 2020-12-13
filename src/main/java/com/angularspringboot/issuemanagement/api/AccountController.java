package com.angularspringboot.issuemanagement.api;

import com.angularspringboot.issuemanagement.dto.LoginRequest;
import com.angularspringboot.issuemanagement.dto.RegistrationRequest;
import com.angularspringboot.issuemanagement.dto.TokenResponse;
import com.angularspringboot.issuemanagement.entity.User;
import com.angularspringboot.issuemanagement.security.JwtTokenUtil;
import com.angularspringboot.issuemanagement.service.impl.UserServiceImpl;
import com.angularspringboot.issuemanagement.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiPaths.AccountCtrl.CTRL)
public class AccountController{

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserServiceImpl userService;

  public AccountController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserServiceImpl userService){
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    final User user = userService.findByUsername(request.getUsername());
    final String token = jwtTokenUtil.generateToken(user);
    return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException{
    Boolean response = userService.register(registrationRequest);
    return ResponseEntity.ok(response);
  }

}
