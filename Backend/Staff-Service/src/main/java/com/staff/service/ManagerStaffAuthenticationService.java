package com.staff.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.staff.client.StaffAuthenticationClient;
import com.staff.model.AuthenticationResponse;



@Service
public class ManagerStaffAuthenticationService {
	@Autowired
	StaffAuthenticationClient staffAuthClient;
	
	public boolean isSessionValid(String token) {

		AuthenticationResponse authenticationResponse = staffAuthClient.getValidity(token);
		if (authenticationResponse == null) {
			throw new RuntimeException("Authentication reponse returned as  NULL");
		}

		String role = authenticationResponse.getRole().substring(5);
		

       if (role.equals("MANAGER"))
            return true;
        else
            return false;
    }}