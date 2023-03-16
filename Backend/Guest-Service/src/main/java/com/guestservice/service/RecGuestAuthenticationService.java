package com.guestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestservice.fiegnclient.GuestAuthenticationClient;
import com.guestservice.model.AuthenticationResponse;

@Service
public class RecGuestAuthenticationService {
	@Autowired
	GuestAuthenticationClient guestAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authenticationResponse = guestAuthClient.getValidity(token);
		if (authenticationResponse == null) {
			throw new RuntimeException("Authentication reponse returned as  NULL");
		}

		String role = authenticationResponse.getRole().substring(5);

		
		 if (role.equals("RECEPTIONIST"))
			return true;
		else
			return false;
	}
}
