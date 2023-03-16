package com.guestservice.service;

import com.guestservice.exception.SequenceException;

public interface SequenceDao {
	
	
	 long getNextSequenceId(String key) throws SequenceException;
	 }


