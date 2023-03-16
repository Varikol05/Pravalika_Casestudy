package com.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.feignclient.InventoryAuthenticationClient;
import com.inventory.model.AuthenticationResponse;


@Service
public class ManagerInventoryAuthenticationService {
	
	@Autowired
	InventoryAuthenticationClient inventoryAuthClient;
	
	public boolean isSessionValid(String token) {

		AuthenticationResponse authenticationResponse = inventoryAuthClient.getValidity(token);
		if (authenticationResponse == null) {
			throw new RuntimeException("Authentication reponse returned as  NULL");
		}

		String role = authenticationResponse.getRole().substring(5);
		

        if (role.equals("MANAGER"))
            return true;
        else
            return false;
    }}