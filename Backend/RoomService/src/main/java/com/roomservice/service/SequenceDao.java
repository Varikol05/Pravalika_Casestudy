package com.roomservice.service;

import com.roomservice.exception.SequenceException;

public interface SequenceDao {
	
	
	 long getNextSequenceId(String key) throws SequenceException;
	 }


