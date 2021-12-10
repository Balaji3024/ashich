package com.login.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.config.JwtUtils;
import com.login.model.JwtRequest;
import com.login.model.JwtResponse;
import com.login.model.User;


@CrossOrigin("*")
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	// generate Token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {

			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not Found");
		}
		// authenticate
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("USER DISABLE " + e.getMessage());

		} catch (BadCredentialsException e) {

			throw new Exception("Invalid credentials " + e.getMessage());

		}

	}
	
	//Return details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		
	 return	((User)this.userDetailsService.loadUserByUsername(principal.getName()));
	}
}
