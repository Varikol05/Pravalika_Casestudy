package com.reservation.service;

import com.reservation.exception.SequenceException;

public interface SequenceDao {
	
	
	 long getNextSequenceId(String key) throws SequenceException;
	 }


