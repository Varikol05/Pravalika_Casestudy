package com.inventory.service;

import com.inventory.exception.SequenceException;

public interface SequenceDao {
	
	
	 long getNextSequenceId(String key) throws SequenceException;
	 }


