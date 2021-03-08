package com.todolist.challenge.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.challenge.message.request.LoginForm;
import com.todolist.challenge.message.response.JwtResponse;
import com.todolist.challenge.security.jwt.JwtProvider;
import com.todolist.challenge.security.services.UserPrinciple;

@RestController
public class AuthRestAPIs {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        UserPrinciple userPrinciple = (UserPrinciple)authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        
        return ResponseEntity.ok(new JwtResponse(jwt, String.valueOf(userPrinciple.getId()), userPrinciple.getUsername(), convertToRoles(userPrinciple.getAuthorities())));
    }
    
    private List<String> convertToRoles(Collection<? extends GrantedAuthority> grantedAuthority) {
		List<String> authorities = new ArrayList<>();
		grantedAuthority.forEach(authority -> authorities.add(authority.getAuthority()));
		return authorities;
	}
}