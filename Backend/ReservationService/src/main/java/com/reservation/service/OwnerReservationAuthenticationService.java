package com.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.feignclient.ReservationAuthenticationClient;
import com.reservation.model.AuthenticationResponse;

@Service
public class OwnerReservationAuthenticationService {
	@Autowired
	ReservationAuthenticationClient reservationAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authenticationResponse = reservationAuthClient.getValidity(token);
		if (authenticationResponse == null) {
			throw new RuntimeException("Authentication reponse returned as  NULL");
		}

		String role = authenticationResponse.getRole().substring(5);

		if (role.equals("OWNER"))
			return true;

		
		else
			return false;
	}
}
